package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 内容DTO
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
@ApiModel("内容DTO")
public class ContentDTO {
    /**
     * 内容
     */
    @ApiModelProperty("内容")
    @NotEmpty(message = "内容不能为空")
    private String content;
    /**
     * 类型
     */
    @ApiModelProperty("内容类型")
    @NotNull(message = "内容类型不能为空")
    private Integer type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
