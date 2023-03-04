package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* 通用查询 DTO
*
* author: huangqj
* date: 2022-10-31 16:38:56
*/
@ApiModel("通用查询DTO")
public class CommonQueryDTO extends PageQueryDTO {

    /**
     * q
     */
    @ApiModelProperty("q")
    private String q;

    public CommonQueryDTO () {

    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

}