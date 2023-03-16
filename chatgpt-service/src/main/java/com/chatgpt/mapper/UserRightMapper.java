package com.chatgpt.mapper;

import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.domain.UserRight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户权益 mapper
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
public interface UserRightMapper {
    /**
     * 查询列表
     *
     * @param queryBean
     * @return
     */
    List<UserRight> selectByQuery(CommonQueryBean queryBean);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    UserRight selectByPrimaryKey(@Param("id") Long id);

    /**
     * 通过用户Id查询
     *
     * @param userId
     * @return
     */
    UserRight selectByUserId(@Param("userId") Long userId);

    /**
     * 新增
     *
     * @param domain
     */
    void insertUserRight(UserRight domain);

    /**
     * 更新
     *
     * @param domain
     */
    void updateUserRight(UserRight domain);

    /**
     * 扣减可用次数
     *
     * @param userId
     */
    void reduceAvailableTimes(@Param("userId") Long userId);

}