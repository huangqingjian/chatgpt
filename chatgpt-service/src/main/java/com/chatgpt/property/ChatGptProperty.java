package com.chatgpt.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * chatgpt属性
 */
@Component
@ConfigurationProperties("chatgpt")
public class ChatGptProperty {
    /**
     * api key
     */
    private String token;
    /**
     * 请求超时，单位为秒
     */
    private Integer requestTimeout;
    /**
     * 模型
     */
    private String model;
    /**
     * 回答的准确性，介于0-2之间，越大回答与问题越不想干，可以认为思想越跳越，这里建议取0.9
     */
    private Double temperature;
    /**
     * 回答结果的分词数量，越小输出的答案字数越少，这里是指答案返回的字数少，而不是答案更简洁的文字输出
     */
    private Integer maxTokens;
    /**
     * 提问者
     */
    private String questioner;
    /**
     * 回答者
     */
    private String answer;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public String getQuestioner() {
        return questioner;
    }

    public void setQuestioner(String questioner) {
        this.questioner = questioner;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
