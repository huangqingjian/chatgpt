package com.chatgpt.configuration.exception;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 错误页配置
 */
@Configuration
public class ErrorPageConfiguration implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage page401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/user/login.html");
        ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
        ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
        registry.addErrorPages(page401, page404, page500);
    }
}

