package com.chatgpt.configuration.secure;

import com.alibaba.fastjson.JSON;
import com.chatgpt.constant.Constant;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.util.JwtUtils;
import com.chatgpt.util.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Security认证成功Handler
 */
public class SecureAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(SecureAuthenticationSuccessHandler.class);

    private StringRedisTemplate stringRedisTemplate;

    public SecureAuthenticationSuccessHandler(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // 生成token
        UserDetailsDTO user = (UserDetailsDTO) authentication.getPrincipal();
        String token = JwtUtils.sign(String.valueOf(user.getId())) ;
        // 设置cookie
        Cookie cookie = new Cookie(Constant.MANAGE_SESSION, token);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        httpServletResponse.addCookie(cookie);
        String cacheKey = Constant.MANAGE_SESSION_PREFIX + user.getId();
        stringRedisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(user), Constant.SESSION_EXPIRATION, TimeUnit.SECONDS);
        ServletUtil.write(httpServletResponse, JSON.toJSONString(ResponseDTO.success("登录成功~")));
    }
}
