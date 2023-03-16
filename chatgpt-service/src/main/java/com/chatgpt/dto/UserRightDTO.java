package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 用户权益DTO
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
@ApiModel("用户权益DTO")
public class UserRightDTO extends BaseDTO {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private Long userId;

    /**
     * 可用次数
     */
    @ApiModelProperty("可用次数")
    private Integer availableTimes;

    /**
     * 可用开始时间
     */
    @ApiModelProperty("可用开始时间")
    private Date startAvailableTime;

    /**
     * 可用结束时间
     */
    @ApiModelProperty("可用结束时间")
    private Date endAvailableTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(Integer availableTimes) {
        this.availableTimes = availableTimes;
    }

    public Date getStartAvailableTime() {
        return startAvailableTime;
    }

    public void setStartAvailableTime(Date startAvailableTime) {
        this.startAvailableTime = startAvailableTime;
    }

    public Date getEndAvailableTime() {
        return endAvailableTime;
    }

    public void setEndAvailableTime(Date endAvailableTime) {
        this.endAvailableTime = endAvailableTime;
    }
}