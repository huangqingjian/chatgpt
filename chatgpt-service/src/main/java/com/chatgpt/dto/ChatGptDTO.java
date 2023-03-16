package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* ChatGpt DTO
*
* author: huangqj
* date: 2022-11-10 10:43:48
*/
@ApiModel("ChatGpt DTO")
public class ChatGptDTO {
    /**
     * 是否成功
     */
    @ApiModelProperty("是否成功")
    private Boolean successed = true;

    /**
     * 回复内容
     */
    @ApiModelProperty("回复内容")
    private String answer;

    public ChatGptDTO successed(Boolean successed) {
        this.setSuccessed(successed);
        return this;
    }

    public ChatGptDTO answer(String answer) {
        this.setAnswer(answer);
        return this;
    }

    public Boolean getSuccessed() {
        return successed;
    }

    public void setSuccessed(Boolean successed) {
        this.successed = successed;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}