package com.chatgpt.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * 聊天事件
 */
public class ChatEvent extends ApplicationEvent {
    private Long chatId;
    private String question;
    private String answer;

    public ChatEvent(Object source, Long chatId, String question, String answer) {
        super(source);
        this.chatId = chatId;
        this.question = question;
        this.answer = answer;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
