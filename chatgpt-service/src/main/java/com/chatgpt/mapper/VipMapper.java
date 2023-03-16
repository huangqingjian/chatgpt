package com.chatgpt.mapper;

import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.domain.Vip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员 mapper
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
public interface VipMapper {
    /**
     * 查询列表
     *
     * @param queryBean
     * @return
     */
    List<Vip> selectByQuery(CommonQueryBean queryBean);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    Vip selectByPrimaryKey(@Param("id") Long id);

    /**
     * 新增
     *
     * @param domain
     */
    void insertProduct(Vip domain);

    /**
     * 更新
     *
     * @param domain
     */
    void updateProduct(Vip domain);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    void deleteByPrimaryKey(@Param("id") Long id);
}