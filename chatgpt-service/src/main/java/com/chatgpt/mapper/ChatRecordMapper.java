package com.chatgpt.mapper;

import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.domain.ChatRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 聊天记录 mapper
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
public interface ChatRecordMapper {
    /**
     * 查询列表
     *
     * @param queryBean
     * @return
     */
    List<ChatRecord> selectByQuery(CommonQueryBean queryBean);

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    ChatRecord selectByPrimaryKey(@Param("id") Long id);

    /**
     * 通过聊天Id查询
     *
     * @param chatId
     * @return
     */
    List<ChatRecord> selectByChatId(@Param("chatId") Long chatId);

    /**
     * 通过用户Id查询最新聊天记录
     *
     * @param userId
     * @return
     */
    List<ChatRecord> selectLastestByUserId(@Param("userId") Long userId);

    /**
     * 新增
     *
     * @param domain
     */
    void insertChatRecord(ChatRecord domain);

    /**
     * 更新
     *
     * @param domain
     */
    void updateChatRecord(ChatRecord domain);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    void deleteByPrimaryKey(@Param("id") Long id);
}