package com.chatgpt.configuration.secure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Security 配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperty.class)
public class SecureConfiguration extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_PAGE = "/login.html";
    private static final String LOGIN_URL = "/login";

    @Resource
    private SecurityProperty securityProperty;
    @Resource
    private UserDetailsService securityUserDetailsService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 配置securitys控制逻辑
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(securityProperty.getOpenApi()).permitAll()
                // 其他的需要登录后才能访问
                .anyRequest().authenticated()
                .and()
                // 验证码验证类
                .addFilterBefore(new SecureCaptchaSupport(stringRedisTemplate), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(manageUsernamePasswordAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new ManageAuthenticationFilter(stringRedisTemplate, securityProperty.getOpenApi()), BasicAuthenticationFilter.class)
                .httpBasic()
                .authenticationEntryPoint(secureAuthenticationEntryPoint())
                .and()
                .formLogin()
                // 登录页面
                .loginPage(LOGIN_PAGE)
                // 登录接口
                .loginProcessingUrl(LOGIN_URL)
                .and()
                .logout()
                .addLogoutHandler(securityLogoutHandler())
                // 配置用户登出自定义处理类
                .logoutSuccessHandler(secureLogoutSuccessHandler())
                .and()
                .exceptionHandling()
                // 配置没有权限自定义处理类
                .accessDeniedHandler(secureAccessDeniedHandler());
        // 取消跨站请求伪造防护
        http.csrf().disable();
        // 防止iframe 造成跨域
        http.headers().frameOptions().disable();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(securePermissionSupport());
        return handler;
    }

    public ManageUsernamePasswordAuthenticationFilter manageUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager){
        ManageUsernamePasswordAuthenticationFilter manageUsernamePasswordAuthenticationFilter = new ManageUsernamePasswordAuthenticationFilter(authenticationManager);
        manageUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(secureAuthenticationSuccessHandler());
        manageUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(secureAuthenticationFailureHandler());
        return manageUsernamePasswordAuthenticationFilter;
    }

    @Bean
    public SecurePermissionSupport securePermissionSupport() {
        return new SecurePermissionSupport();
    }

    @Bean
    public SecureAuthenticationEntryPoint secureAuthenticationEntryPoint() {
        return new SecureAuthenticationEntryPoint();
    }

    @Bean
    public SecureAuthenticationSuccessHandler secureAuthenticationSuccessHandler() {
        return new SecureAuthenticationSuccessHandler(stringRedisTemplate);
    }

    @Bean
    public SecureAuthenticationFailureHandler secureAuthenticationFailureHandler() {
        return new SecureAuthenticationFailureHandler();
    }

    @Bean
    public SecureLogoutSuccessHandler secureLogoutSuccessHandler() {
        return new SecureLogoutSuccessHandler();
    }

    @Bean
    public SecureAccessDeniedHandler secureAccessDeniedHandler() {
        return new SecureAccessDeniedHandler();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecureLogoutHandler securityLogoutHandler() {
        return new SecureLogoutHandler(stringRedisTemplate);
    }
}
