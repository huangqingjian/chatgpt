package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 聊天记录DTO
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
@ApiModel(description = "聊天记录DTO")
public class ChatRecordDTO extends BaseDTO {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 问题
     */
    @ApiModelProperty("问题")
    private String question;

    /**
     * 答案
     */
    @ApiModelProperty("答案")
    private String answer;

    /**
     * 聊天Id
     */
    @ApiModelProperty("聊天Id")
    private Long chatId;

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private Long userId;

    /**
     * 聊天时间
     */
    @ApiModelProperty("聊天时间")
    private Date chatTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getChatTime() {
        this.chatTime = getCreateTime();
        return chatTime;
    }

    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }
}