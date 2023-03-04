package com.chatgpt.service.impl;

import com.alibaba.fastjson.JSON;
import com.chatgpt.constant.Constant;
import com.chatgpt.dto.ContentDTO;
import com.chatgpt.dto.QuestionDTO;
import com.chatgpt.enums.ContentType;
import com.chatgpt.exception.ServiceException;
import com.chatgpt.property.ChatGptProperty;
import com.chatgpt.service.ChatService;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
* 聊天服务
*
* author: huangqj
* date: 2022-11-20 11:27:16
*/
@Service
public class ChatServiceImpl implements ChatService {
    private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);
    @Autowired
    private ChatGptProperty chatGptProperty;

    /**
     * 聊天
     *
     * @param question
     * @return
     */
    public String chat(QuestionDTO question) {
        log.info("the {}.chat parameter: [{}]", this.getClass().getSimpleName(), question);
        List<ContentDTO> contents = question.getContents();
        if(CollectionUtils.isEmpty(contents)) {
            throw new ServiceException("请输入您的问题～");
        }
        // 组装内容
        StringBuffer sb = new StringBuffer();
        if(Objects.equals(Boolean.TRUE, question.getCoherented())) {
            for (ContentDTO content : contents) {
                if (Objects.equals(ContentType.QUESTION.getValue(), content.getType())) {
                    sb.append(chatGptProperty.getQuestioner().concat(Constant.MAOHAO));
                } else if (Objects.equals(ContentType.ANSWER.getValue(), content.getType())) {
                    sb.append(chatGptProperty.getAnswer().concat(Constant.MAOHAO));
                }
                sb.append(content.getContent());
                sb.append(Constant.TNN);
            }
            sb.append(chatGptProperty.getAnswer().concat(Constant.MAOHAO));
        } else {
            sb.append(chatGptProperty.getQuestioner().concat(Constant.MAOHAO));
            sb.append(contents.get(contents.size() - 1).getContent());
            sb.append(Constant.TNN);
            sb.append(chatGptProperty.getAnswer().concat(Constant.MAOHAO));
        }
        // 请求chatgpt
        OpenAiService service = new OpenAiService(chatGptProperty.getToken(), Duration.ofSeconds(chatGptProperty.getRequestTimeout()));
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(sb.toString())
                .model(chatGptProperty.getModel())
                .temperature(chatGptProperty.getTemperature())
                .maxTokens(chatGptProperty.getMaxTokens())
                .build();
        // 响应结果处理
        List<CompletionChoice> completionChoices = service.createCompletion(completionRequest).getChoices();
        if(CollectionUtils.isEmpty(completionChoices)) {
            log.error("ChatGpt Answer:{}", JSON.toJSONString(completionChoices));
            throw new ServiceException("ChatGpt发生了意外～");
        }
        return completionChoices.get(completionChoices.size() - 1).getText().replace(chatGptProperty.getAnswer().concat(Constant.MAOHAO), Constant.EMPTY);
    }
}
