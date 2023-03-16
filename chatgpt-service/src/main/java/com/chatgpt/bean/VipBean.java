package com.chatgpt.bean;

import com.chatgpt.domain.BaseDomain;

import java.math.BigDecimal;

/**
 * 会员 Bean
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
public class VipBean extends BaseBean {
    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 有效期
     */
    private Integer period;

    /**
     * 次数
     */
    private Integer times;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 描述
     */
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