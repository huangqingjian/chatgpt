package com.chatgpt.ai;

import com.alibaba.fastjson.JSON;
import com.chatgpt.ai.request.ChatCompletionRequest;
import com.chatgpt.ai.response.ChatCompletionResult;
import com.chatgpt.util.HttpClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChatGptService {
    private static final String BASE_URL = "https://api.openai.com";
    /**
     * gpt4模型url
     */
    private static final String CHAT_COMPLETION_URL = "/v1/chat/completions";

    /**
     *
     *
     * @param token
     * @param socketTimeout
     * @param request
     * @return
     */
    public ChatCompletionResult createChatCompletion(String token, Integer socketTimeout, ChatCompletionRequest request) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        String result = HttpClient.doPostJson(BASE_URL.concat(CHAT_COMPLETION_URL), headers, socketTimeout * 1000, JSON.toJSONString(request));
        return JSON.parseObject(result, ChatCompletionResult.class);
    }

}
