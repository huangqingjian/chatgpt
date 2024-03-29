package com.chatgpt.bean;

/**
* 通用查询 Bean
*
* author: huangqj
* date: 2022-10-31 16:38:56
*/
public class CommonQueryBean extends PageQueryBean {

    /**
     * q
     */
    private String q;

    /**
     * 用户Id
     */
    private Long userId;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}