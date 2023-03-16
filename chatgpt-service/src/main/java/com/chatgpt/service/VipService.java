package com.chatgpt.service;

import com.chatgpt.dto.CommonQueryDTO;
import com.chatgpt.dto.PageDTO;
import com.chatgpt.dto.VipBuyDTO;
import com.chatgpt.dto.VipDTO;

import java.util.List;

/**
 * Vip服务
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
public interface VipService {
    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    PageDTO<VipDTO> list(CommonQueryDTO queryDTO);

    /**
     * 查询
     *
     * @return
     */
    List<VipDTO> listAll();

    /**
     * 查找
     *
     * @param id
     * @return
     */
    VipDTO find(Long id);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long save(VipDTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    Long update(VipDTO dto);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * vip购买
     *
     * @param vipBuy
     */
    void buy(VipBuyDTO vipBuy);

}
