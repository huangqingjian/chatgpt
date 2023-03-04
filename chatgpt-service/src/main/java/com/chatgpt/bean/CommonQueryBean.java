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

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

}