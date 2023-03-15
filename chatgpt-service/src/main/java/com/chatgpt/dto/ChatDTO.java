package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 聊天DTO
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
@ApiModel(description = "聊天DTO")
public class ChatDTO extends BaseDTO {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 最新聊天记录
     */
    @ApiModelProperty("最新聊天记录")
    private ChatRecordDTO lastest;

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatRecordDTO getLastest() {
        return lastest;
    }

    public void setLastest(ChatRecordDTO lastest) {
        this.lastest = lastest;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}