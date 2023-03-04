package com.chatgpt.mapper;

import com.chatgpt.bean.UserQueryBean;
import com.chatgpt.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工 mapper
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
public interface UserMapper {
    /**
     * 查询列表
     *
     * @param queryBean
     * @return
     */
    List<User> selectByQuery(UserQueryBean queryBean);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(@Param("id") Long id);

    /**
     * 通过主键批量查询
     *
     * @param ids
     * @return
     */
    List<User> selectByPrimaryKeys(@Param("ids") List<Long> ids);

    /**
     * 通过手机查询
     *
     * @param mobile
     * @return
     */
    User selectByMobile(@Param("mobile") String mobile);

    /**
     * 通过手机和类型查询
     *
     * @param mobile
     * @param type
     * @return
     */
    User selectByMobileAndType(@Param("mobile") String mobile, @Param("type") Integer type);

    /**
     * 新增
     *
     * @param domain
     */
    void insertUser(User domain);

    /**
     * 更新
     *
     * @param domain
     */
    void updateUser(User domain);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    void deleteByPrimaryKey(@Param("id") Long id);
}