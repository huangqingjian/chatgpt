package com.chatgpt.listener;

import com.chatgpt.context.UserContext;
import com.chatgpt.dto.ChatRecordDTO;
import com.chatgpt.listener.event.ChatEvent;
import com.chatgpt.service.ChatRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 聊天事件
 */
@Component
public class ChatListener implements ApplicationListener<ChatEvent> {
    private static final Logger log = LoggerFactory.getLogger(ChatListener.class);

    @Autowired
    private ChatRecordService chatRecordService;

    @Override
    public void onApplicationEvent(ChatEvent event) {
        // TODO 待异步处理
        // 保存聊天记录
        ChatRecordDTO chatRecord = new ChatRecordDTO();
        chatRecord.setQuestion(event.getQuestion());
        chatRecord.setAnswer(event.getAnswer());
        chatRecord.setChatId(event.getChatId());
        chatRecord.setUserId(UserContext.getUser());
        chatRecordService.save(chatRecord);
    }
}
