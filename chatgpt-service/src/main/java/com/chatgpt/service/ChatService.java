package com.chatgpt.service;

import com.chatgpt.dto.*;

import java.util.List;

/**
 * 聊天服务
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
public interface ChatService {
    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    PageDTO<ChatDTO> list(CommonQueryDTO queryDTO);

    /**
     * 查找
     *
     * @param id
     * @return
     */
    ChatDTO find(Long id);

    /**
     * 查找
     *
     * @param userId
     * @return
     */
    List<ChatDTO> listByUserId(Long userId);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Long save(ChatDTO dto);

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    Long update(ChatDTO dto);

    /**
     * 删除
     *
     * @param chatId
     */
    void delete(Long chatId);

    /**
     * 聊天
     *
     * @param question
     * @return
     */
    AnswerDTO chat(QuestionDTO question);
}
