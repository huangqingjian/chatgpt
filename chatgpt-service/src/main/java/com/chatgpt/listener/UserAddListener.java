package com.chatgpt.listener;

import com.chatgpt.dto.UserRightDTO;
import com.chatgpt.dto.UserRightDetailDTO;
import com.chatgpt.enums.UserRightSource;
import com.chatgpt.listener.event.UserAddEvent;
import com.chatgpt.service.ChatRecordService;
import com.chatgpt.service.UserRightDetailService;
import com.chatgpt.service.UserRightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 添加用户监听
 */
@Component
public class UserAddListener implements ApplicationListener<UserAddEvent> {
    private static final Logger log = LoggerFactory.getLogger(UserAddListener.class);

    @Autowired
    private UserRightService userRightService;
    @Autowired
    private UserRightDetailService userRightDetailService;

    @Value("${free.available.times:2}")
    private Integer freeAvailableTimes;

    @Override
    public void onApplicationEvent(UserAddEvent event) {
        // 初始化用户权益
        UserRightDTO userRight = new UserRightDTO();
        userRight.setUserId(event.getUserId());
        userRight.setAvailableTimes(freeAvailableTimes);
        Long rightId = userRightService.save(userRight);
        // 记录变动权益明细
        UserRightDetailDTO userRightDetail = new UserRightDetailDTO();
        userRightDetail.setRightId(rightId);
        userRightDetail.setDesc(String.format("新用户免费赠送%s次聊天次数", freeAvailableTimes));
        userRightDetail.setSource(UserRightSource.SYSTEM.getValue());
        userRightDetailService.save(userRightDetail);
    }
}
