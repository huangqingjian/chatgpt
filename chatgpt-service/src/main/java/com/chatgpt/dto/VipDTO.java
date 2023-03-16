package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 会员DTO
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
@ApiModel("分页查询DTO")
public class VipDTO extends BaseDTO {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 有效期
     */
    @ApiModelProperty("有效期")
    private Integer period;

    /**
     * 次数
     */
    @ApiModelProperty("次数")
    private Integer times;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private BigDecimal price;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        if(times < 0) {
            times = null;
        }
        this.times = times;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}