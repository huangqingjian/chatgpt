package com.chatgpt.listener.event;

import com.chatgpt.enums.AvailableUserRight;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * 聊天事件
 */
public class ChatEvent extends ApplicationEvent {
    /**
     * 聊天Id
     */
    private Long chatId;
    /**
     * 问题
     */
    private String question;
    /**
     * 答复
     */
    private String answer;
    /**
     * 提问时间
     */
    private Date questionTime;
    /**
     * 答复时间
     */
    private Date answerTime;
    /**
     * 可用用户权益
     */
    private AvailableUserRight availableUserRight;

    public ChatEvent(Object source) {
        super(source);
    }

    public ChatEvent chatId(Long chatId) {
        this.setChatId(chatId);
        return this;
    }

    public ChatEvent question(String question) {
        this.setQuestion(question);
        return this;
    }

    public ChatEvent answer(String answer) {
        this.setAnswer(answer);
        return this;
    }

    public ChatEvent questionTime(Date questionTime) {
        this.setQuestionTime(questionTime);
        return this;
    }

    public ChatEvent answerTime(Date answerTime) {
        this.setAnswerTime(answerTime);
        return this;
    }

    public ChatEvent availableUserRight(AvailableUserRight availableUserRight) {
        this.setAvailableUserRight(availableUserRight);
        return this;
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

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public AvailableUserRight getAvailableUserRight() {
        return availableUserRight;
    }

    public void setAvailableUserRight(AvailableUserRight availableUserRight) {
        this.availableUserRight = availableUserRight;
    }
}
