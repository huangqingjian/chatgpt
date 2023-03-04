package com.chatgpt.configuration.secure;

import com.alibaba.fastjson.JSON;
import com.chatgpt.constant.Constant;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.util.ServletUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Security权限注解实现
 */
public class SecureCaptchaSupport extends OncePerRequestFilter implements Filter {
    /**
     * 需要过滤的接口条件
     * */
    private String defaultFilterProcessUrl = "/login";
    private String method = "POST";

    private StringRedisTemplate stringRedisTemplate;

    public SecureCaptchaSupport(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 验证码校检逻辑
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (method.equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getRequestURI())) {
            String captcha = request.getParameter("captcha");
            response.setContentType("application/json;charset=UTF-8");
            if (StringUtils.isEmpty(captcha)) {
                ServletUtil.write(response, JSON.toJSONString(ResponseDTO.fail("验证码不能为空～")));
                return;
            }
            String token = request.getParameter("token");
            String cachedCaptcha = stringRedisTemplate.opsForValue().get(Constant.MANAGE_CAPTCHA_PREFIX + token);
            if(!Objects.equals(captcha, cachedCaptcha)) {
                ServletUtil.write(response, JSON.toJSONString(ResponseDTO.fail("验证码错误～")));
                return;
            }
            stringRedisTemplate.delete(Constant.MANAGE_CAPTCHA_PREFIX + token);
        }
        chain.doFilter(request, response);
    }
}