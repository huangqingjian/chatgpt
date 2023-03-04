package com.chatgpt.configuration.secure;

import com.alibaba.fastjson.JSON;
import com.chatgpt.constant.Constant;
import com.chatgpt.util.JwtUtils;
import com.chatgpt.util.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * web端自定义BasicAuthenticationFilter
 */
public class UserAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(WebUsernamePasswordAuthenticationFilter.class);

    private StringRedisTemplate stringRedisTemplate;
    private String[] staticUrl;
    private String[] openApi;

    public UserAuthenticationFilter(StringRedisTemplate stringRedisTemplate, String[] staticUrl, String[] openApi) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.staticUrl = staticUrl;
        this.openApi = openApi;
    }

    public void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws IOException, ServletException {
        if(!isStaticUrl(httpServletRequest.getRequestURI())) {
            String token = ServletUtil.getWebToken(httpServletRequest);
            if (!StringUtils.isEmpty(token)) {
                // 获取用户
                String userStr = stringRedisTemplate.opsForValue().get(Constant.WEB_SESSION_PREFIX + JwtUtils.getUsername(token));
                UserDetailsDTO user = JSON.parseObject(userStr, UserDetailsDTO.class);
                if(user != null) {
                    UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authResult);
                }
            }
            if(SecurityContextHolder.getContext().getAuthentication() == null) {
                if(!isOpenApi(httpServletRequest.getRequestURI())) {
                    throw new AuthenticationException("请登录后访问～");
                }
            }
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    private PathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 是否免验证api
     *
     * @param url
     * @return
     */
    protected boolean isOpenApi(String url) {
        for(String pattern : openApi) {
            boolean flag = pathMatcher.match(pattern, url);
            if(flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否静态资源
     *
     * @param url
     * @return
     */
    protected boolean isStaticUrl(String url) {
        for(String pattern : staticUrl) {
            boolean flag = pathMatcher.match(pattern, url);
            if(flag) {
                return true;
            }
        }
        return false;
    }

}
