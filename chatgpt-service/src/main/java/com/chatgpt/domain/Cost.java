package com.chatgpt.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 消费
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
public class Cost extends BaseDomain {
    /**
     * id
     */
    private Long id;

    /**
     * 充值单号
     */
    private String costNo;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 会员Id
     */
    private Long vipId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 支付状态
     */
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