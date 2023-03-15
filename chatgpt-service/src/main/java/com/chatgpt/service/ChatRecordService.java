package com.chatgpt.service;

import com.chatgpt.dto.ChatRecordDTO;
import com.chatgpt.dto.CommonQueryDTO;
import com.chatgpt.dto.PageDTO;

import java.util.List;

/**
 * 聊天记录服务
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
public interface ChatRecordService {
    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    PageDTO<ChatRecordDTO> list(CommonQueryDTO queryDTO);

    /**
     * 查找
     *
     * @param id
     * @return
     */
    ChatRecordDTO find(Long id);

    /**
     * 查找
     *
     * @param chatId
     * @return
     */
    List<ChatRecordDTO> listByChatId(Long chatId);

    /**
     * 查找用户最新
     *
     * @param userId
     * @return
     */
    List<ChatRecordDTO> listLastestByUserId(Long userId);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long save(ChatRecordDTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    Long update(ChatRecordDTO dto);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

}
