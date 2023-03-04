package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询DTO
 */
@ApiModel("分页查询DTO")
public class PageQueryDTO {
    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private Integer page = 0;

    /**
     * 每页记录数
     */
    @ApiModelProperty("每页记录数")
    private Integer limit = 10;

    /**
     * 排序字段名
     */
    @ApiModelProperty("排序字段名")
    private String order = "id";

    /**
     * 排序顺序
     */
    @ApiModelProperty("排序顺序")
    private String orderDesc = "desc";

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

}
