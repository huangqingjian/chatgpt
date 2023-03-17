package com.chatgpt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 消费DTO
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
@ApiModel("内容DTO")
public class CostDTO extends BaseDTO {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 充值单号
     */
    @ApiModelProperty("充值单号")
    private String costNo;

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private Long userId;

    /**
     * 会员Id
     */
    @ApiModelProperty("会员Id")
    private Long vipId;

    /**
     * 会员名
     */
    @ApiModelProperty("会员名")
    private String vipName;

    /**
     * 金额
     */
    @ApiModelProperty("金额")
    private BigDecimal amount;

    /**
     * 下单时间
     */
    @ApiModelProperty("下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    /**
     * 支付时间
     */
    @ApiModelProperty("支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**
     * 支付状态
     */
    @ApiModelProperty("支付状态")
    private Integer payStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCostNo() {
        return costNo;
    }

    public void setCostNo(String costNo) {
        this.costNo = costNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVipId() {
        return vipId;
    }

    public void setVipId(Long vipId) {
        this.vipId = vipId;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
}