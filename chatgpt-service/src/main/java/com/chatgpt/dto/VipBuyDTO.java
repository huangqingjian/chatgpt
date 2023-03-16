package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * vip购买DTO
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
@ApiModel("vip购买DTO")
public class VipBuyDTO {
    /**
     * 会员Id
     */
    @ApiModelProperty("会员Id")
    @NotNull(message = "会员不能为空")
    private Long vipId;

    public Long getVipId() {
        return vipId;
    }

    public void setVipId(Long vipId) {
        this.vipId = vipId;
    }
}