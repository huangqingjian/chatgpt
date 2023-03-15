package com.chatgpt.domain;

/**
 * 聊天
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
public class Chat extends BaseDomain {
    /**
     * id
     */
    private Long id;

    /**
     * 用户Id
     */
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}