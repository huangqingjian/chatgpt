package com.chatgpt.bean;

import com.chatgpt.dto.CommonQueryDTO;

/**
* 用户查询 Bean
*
* author: huangqj
* date: 2022-10-31 16:38:56
*/
public class UserQueryBean extends CommonQueryDTO {

    /**
     * 用户类型
     */
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}