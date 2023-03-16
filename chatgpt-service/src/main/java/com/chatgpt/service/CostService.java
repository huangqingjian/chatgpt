package com.chatgpt.service;

import com.chatgpt.dto.CostDTO;
import com.chatgpt.dto.CommonQueryDTO;
import com.chatgpt.dto.PageDTO;

import java.util.List;

/**
 * 消费服务
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
public interface CostService {
    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    PageDTO<CostDTO> list(CommonQueryDTO queryDTO);

    /**
     * 查询
     *
     * @param userId
     * @return
     */
    List<CostDTO> listByUserId(Long userId);

    /**
     * 查找
     *
     * @param id
     * @return
     */
    CostDTO find(Long id);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long save(CostDTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    Long update(CostDTO dto);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

}
