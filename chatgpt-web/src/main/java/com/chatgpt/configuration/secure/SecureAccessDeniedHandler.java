package com.chatgpt.configuration.secure;

import com.alibaba.fastjson.JSON;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.util.ServletUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Security无权限处理Handler
 */
public class SecureAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        if (ServletUtil.isAjax(httpServletRequest)) {
            ResponseDTO response = ResponseDTO.fail(403, "您无权限~");
            ServletUtil.write(httpServletResponse, JSON.toJSONString(response));
        } else {
            httpServletResponse.sendRedirect("/error/403");
        }
    }
}
