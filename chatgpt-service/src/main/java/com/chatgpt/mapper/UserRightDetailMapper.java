package com.chatgpt.mapper;

import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.domain.UserRightDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户权益明细 mapper
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
public interface UserRightDetailMapper {
    /**
     * 查询列表
     *
     * @param queryBean
     * @return
     */
    List<UserRightDetail> selectByQuery(CommonQueryBean queryBean);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    UserRightDetail selectByPrimaryKey(@Param("id") Long id);

    /**
     * 通过权益Id查询
     *
     * @param rightId
     * @return
     */
    List<UserRightDetail> selectByRightId(@Param("rightId") Long rightId);

    /**
     * 新增
     *
     * @param domain
     */
    void insertUserRightDetail(UserRightDetail domain);

    /**
     * 更新
     *
     * @param domain
     */
    void updateUserRightDetail(UserRightDetail domain);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    void deleteByPrimaryKey(@Param("id") Long id);
}