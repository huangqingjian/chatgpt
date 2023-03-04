package com.chatgpt.configuration.secure;

import com.alibaba.fastjson.JSON;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.util.ServletUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security登出成功Handler
 */
public class SecureLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResponseDTO result = ResponseDTO.success("登出成功～");
        ServletUtil.write(httpServletResponse, JSON.toJSONString(result));
    }
}
