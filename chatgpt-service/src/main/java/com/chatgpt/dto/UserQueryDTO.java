package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* 用户查询 DTO
*
* author: huangqj
* date: 2022-10-31 16:38:56
*/
@ApiModel("用户查询DTO")
public class UserQueryDTO extends CommonQueryDTO {

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}