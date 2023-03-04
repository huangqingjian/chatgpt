package com.chatgpt.configuration.secure;

import com.chatgpt.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义用户注销处理类
 */
public class SecureLogoutHandler implements LogoutHandler {
    private static final Logger log = LoggerFactory.getLogger(SecureLogoutHandler.class);

    private StringRedisTemplate stringRedisTemplate;

    public SecureLogoutHandler(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if(authentication != null) {
            UserDetailsDTO user = (UserDetailsDTO) authentication.getPrincipal();
            if(user != null) {
                stringRedisTemplate.delete(Constant.MANAGE_SESSION_PREFIX + user.getId());
            }
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
    }
}
