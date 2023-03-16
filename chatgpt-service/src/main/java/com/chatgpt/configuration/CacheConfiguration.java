package com.chatgpt.configuration;

import com.chatgpt.dto.ChatDTO;
import com.chatgpt.service.ChatService;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 缓存配置
 */
@Configuration
public class CacheConfiguration {
    @Autowired
    private ChatService chatService;

    /**
     * 会话缓存
     *
     * @return
     */
    @Bean(name = "chatCache")
    public LoadingCache<Long, ChatDTO> chatCache(){
        LoadingCache<Long, ChatDTO> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(2, TimeUnit.MINUTES).softValues().build(new CacheLoader<Long, ChatDTO>() {
                    public ChatDTO load(@NonNull Long key) throws Exception {
                        ChatDTO chat = chatService.find(key);
                        return chat;
                    }
                });
        return cache;
    }


}
