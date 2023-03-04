package com.chatgpt.configuration.secure;

import com.alibaba.fastjson.JSON;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.util.ServletUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security认证失败Handler
 */
public class SecureAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ServletUtil.write(httpServletResponse, JSON.toJSONString(ResponseDTO.fail(HttpStatus.UNAUTHORIZED.value(), "手机号或密码错误~")));
    }
}
