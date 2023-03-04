package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* 回复DTO
*
* author: huangqj
* date: 2022-11-10 10:43:48
*/
@ApiModel("回复DTO")
public class AnswerDTO {
    /**
     * 问题Id
     */
    @ApiModelProperty("问题Id")
    private String questionId;

    /**
     * 回复内容
     */
    @ApiModelProperty("回复内容")
    private String content;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}