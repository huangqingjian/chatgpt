package com.chatgpt.listener;

import com.chatgpt.context.UserContext;
import com.chatgpt.dto.ChatRecordDTO;
import com.chatgpt.enums.AvailableUserRight;
import com.chatgpt.listener.event.ChatEvent;
import com.chatgpt.service.ChatRecordService;
import com.chatgpt.service.UserRightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 聊天监听
 */
@Component
public class ChatListener implements ApplicationListener<ChatEvent> {
    private static final Logger log = LoggerFactory.getLogger(ChatListener.class);

    @Autowired
    private ChatRecordService chatRecordService;
    @Autowired
    private UserRightService userRightService;

    @Override
    public void onApplicationEvent(ChatEvent event) {
        // TODO 待异步处理
        // 保存聊天记录
        ChatRecordDTO chatRecord = new ChatRecordDTO();
        chatRecord.setQuestion(event.getQuestion());
        chatRecord.setQuestionTime(event.getQuestionTime());
        chatRecord.setAnswer(event.getAnswer());
        chatRecord.setAnswerTime(event.getAnswerTime());
        chatRecord.setChatId(event.getChatId());
        chatRecord.setUserId(UserContext.getUser());
        chatRecordService.save(chatRecord);
        // 扣减聊天次数
        if(Objects.equals(event.getAvailableUserRight(), AvailableUserRight.TIMES)) {
            userRightService.reduceAvailableTimes(UserContext.getUser());
        }
    }
}
