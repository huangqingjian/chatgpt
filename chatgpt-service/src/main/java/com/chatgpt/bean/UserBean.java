package com.chatgpt.bean;

import java.util.Date;

/**
* 用户 Bean
*
* author: huangqj
* date: 2022-11-10 10:43:48
*/
public class UserBean extends BaseBean{
    /**
     * id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String face;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 可用次数
     */
    private Integer availableTimes;

    /**
     * 可用开始时间
     */
    private Date startAvailableTime;

    /**
     * 可用结束时间
     */
    private Date endAvailableTime;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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