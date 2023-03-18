package com.chatgpt.ai;

/**
 * Chat Complete Message
 */
public class ChatCompletionMessage {
    /**
     * 角色
     */
    private String role;
    /**
     * 消息
     */
    private String content;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
