package com.chatgpt.service;

import com.chatgpt.dto.CommonQueryDTO;
import com.chatgpt.dto.PageDTO;
import com.chatgpt.dto.UserRightDetailDTO;

import java.util.List;

/**
 * 用户权益明细服务
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
public interface UserRightDetailService {
    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    PageDTO<UserRightDetailDTO> list(CommonQueryDTO queryDTO);

    /**
     * 查询
     *
     * @param rightId
     * @return
     */
    List<UserRightDetailDTO> listByRightId(Long rightId);

    /**
     * 查找
     *
     * @param id
     * @return
     */
    UserRightDetailDTO find(Long id);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long save(UserRightDetailDTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    Long update(UserRightDetailDTO dto);

}
