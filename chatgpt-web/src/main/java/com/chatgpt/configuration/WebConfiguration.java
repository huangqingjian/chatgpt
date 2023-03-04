package com.chatgpt.configuration;

import com.chatgpt.configuration.interceptor.SecurityInterceptor;
import com.chatgpt.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * web配置
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    @Value("${image.dir:/data/images/}")
    private String imageDir;

    /**
     * 静态资源
     *
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/picture/**").addResourceLocations("file:" + imageDir);
        registry.addResourceHandler("/qrcode/**").addResourceLocations("file:" + imageDir);

        super.addResourceHandlers(registry);
    }

    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    /**
     * 拦截器配置
     *
     * @param registry 注册类
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/picture/**")
                .excludePathPatterns("/test/**")
                .excludePathPatterns("/remote/**")
                .excludePathPatterns("/login.html")
                .excludePathPatterns("/login");
        super.addInterceptors(registry);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    /**
     * 消息转换器
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        converters.add(Constant.ZERO, responseBodyConverter());
    }
}
