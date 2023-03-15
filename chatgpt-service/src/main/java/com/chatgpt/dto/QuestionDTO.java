package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 问题DTO
*
* author: huangqj
* date: 2022-11-10 10:43:48
*/
@ApiModel("问题DTO")
public class QuestionDTO {
    /**
     * 问题Id
     */
    @ApiModelProperty("问题Id")
    @NotEmpty(message = "问题Id不能为空")
    private String id;

    /**
     * 聊天室Id
     */
    @ApiModelProperty("聊天室Id")
    @NotNull(message = "聊天室Id不能为空")
    private Long chatId;

    /**
     * 问题内容
     */
    @ApiModelProperty("问题内容")
    @NotNull(message = "问题内容不能为空")
    @Valid
    private List<ContentDTO> contents;

    /**
     * 是否连贯上下文
     */
    @ApiModelProperty("是否连贯上下文")
    private Boolean coherented = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public List<ContentDTO> getContents() {
        return contents;
    }

    public void setContents(List<ContentDTO> contents) {
        this.contents = contents;
    }

    public Boolean getCoherented() {
        return coherented;
    }

    public void setCoherented(Boolean coherented) {
        this.coherented = coherented;
    }
}