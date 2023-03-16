package com.chatgpt.listener.event;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * 添加用户事件
 */
public class UserAddEvent extends ApplicationEvent {
    /**
     * 用户Id
     */
    private Long userId;

    public UserAddEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
