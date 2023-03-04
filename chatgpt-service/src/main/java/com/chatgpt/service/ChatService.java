package com.chatgpt.service;

import com.chatgpt.dto.QuestionDTO;

/**
 * 聊天服务
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
public interface ChatService {
    /**
     * 聊天
     *
     * @param question
     * @return
     */
    String chat(QuestionDTO question);
}
