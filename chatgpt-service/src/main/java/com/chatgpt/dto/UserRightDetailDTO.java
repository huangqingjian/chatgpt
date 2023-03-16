package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户权益明细DTO
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
@ApiModel("用户权益DTO")
public class UserRightDetailDTO extends BaseDTO {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 权益Id
     */
    @ApiModelProperty("权益Id")
    private Long rightId;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String desc;

    /**
     * 来源，1-系统 2-用户
     */
    @ApiModelProperty("来源，1-系统 2-用户")
    private Integer source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRightId() {
        return rightId;
    }

    public void setRightId(Long rightId) {
        this.rightId = rightId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}