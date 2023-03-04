package com.chatgpt.configuration.secure;

import com.alibaba.fastjson.JSON;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.util.ServletUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security验证失败处理Handler
 */
public class SecureAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseDTO response = ResponseDTO.fail(HttpStatus.UNAUTHORIZED.value(), "not authenticated～");
        ServletUtil.write(httpServletResponse, JSON.toJSONString(response));
    }
}
