package com.chatgpt.bean;

/**
 * 分页查询DTO
 */
public class PageQueryBean {
    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页记录数
     */
    private Integer limit;

    /**
     * 排序字段名
     */
    private String order = "id";

    /**
     * 排序顺序
     */
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
