package com.chatgpt.web.controller;

import com.chatgpt.configuration.secure.UserDetailsDTO;
import com.chatgpt.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础控制器
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    protected StringRedisTemplate stringRedisTemplate;

    /**
     * 获取当前用户
     *
     * @return
     */
    public UserDetailsDTO getCurrentUser() {
        return SecurityUtils.getCurrentUser();
    }

    /**
     * 获取当前用户Id
     *
     * @return
     */
    public Long getCurrentUserId() {
        return SecurityUtils.getCurrentUserId();
    }

}
