package com.chatgpt.mapper;

import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.domain.Cost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消费 mapper
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
public interface CostMapper {
    /**
     * 查询列表
     *
     * @param queryBean
     * @return
     */
    List<Cost> selectByQuery(CommonQueryBean queryBean);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    Cost selectByPrimaryKey(@Param("id") Long id);

    /**
     * 通过用户Id查询
     *
     * @param userId
     * @return
     */
    List<Cost> selectByUserId(@Param("userId") Long userId);

    /**
     * 新增
     *
     * @param domain
     */
    void insertCost(Cost domain);

    /**
     * 更新
     *
     * @param domain
     */
    void updateCost(Cost domain);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    void deleteByPrimaryKey(@Param("id") Long id);
}