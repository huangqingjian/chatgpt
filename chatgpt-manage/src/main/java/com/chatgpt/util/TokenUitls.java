package com.chatgpt.util;

import com.chatgpt.constant.Constant;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * token 工具
 */
public class TokenUitls {
    /**
     * 获取token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String esToken = request.getHeader(Constant.MANAGE_SESSION);
        if(!StringUtils.isEmpty(esToken)) {
            return esToken;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constant.MANAGE_SESSION)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
