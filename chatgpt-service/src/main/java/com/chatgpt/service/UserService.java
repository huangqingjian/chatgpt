package com.chatgpt.service;

import com.chatgpt.dto.PageDTO;
import com.chatgpt.dto.PasswordDTO;
import com.chatgpt.dto.UserDTO;
import com.chatgpt.dto.UserQueryDTO;

import java.util.List;

/**
 * 用户服务
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
public interface UserService {
    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    PageDTO<UserDTO> list(UserQueryDTO queryDTO);

    /**
     * 查询
     *
     * @param ids
     * @return
     */
    List<UserDTO> listByIds(List<Long> ids);

    /**
     * 查找
     *
     * @param id
     * @return
     */
    UserDTO find(Long id);

    /**
     * 查找
     *
     * @param mobile
     * @return
     */
    UserDTO findByMobile(String mobile);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long save(UserDTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    Long update(UserDTO dto);

    /**
     * 更新密码
     *
     * @param dto
     * @return
     */
    void updatePassword(PasswordDTO dto);
//
//    /**
//     * 注册
//     *
//     * @param dto
//     * @return
//     */
//    Long register(UserRegisterDTO dto);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 重置密码
     *
     * @param id
     * @param password
     */
    void resetPassword(Long id, String password);
}
