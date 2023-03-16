package com.chatgpt.service;

import com.chatgpt.dto.CommonQueryDTO;
import com.chatgpt.dto.UserRightDTO;
import com.chatgpt.dto.PageDTO;

import java.util.List;

/**
 * 用户权益服务
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
public interface UserRightService {
    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    PageDTO<UserRightDTO> list(CommonQueryDTO queryDTO);

    /**
     * 查找
     *
     * @param userId
     * @return
     */
    UserRightDTO findByUserId(Long userId);

    /**
     * 查找
     *
     * @param id
     * @return
     */
    UserRightDTO find(Long id);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long save(UserRightDTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    Long update(UserRightDTO dto);

    /**
     * 扣减可用次数
     *
     * @param userId
     */
    void reduceAvailableTimes(Long userId);

}
