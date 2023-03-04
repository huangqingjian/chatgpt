package com.chatgpt.util;

import com.chatgpt.constant.Constant;

import java.util.UUID;

/**
 * id生成工具类
 */
public class IdGenerateUtils {
    /**
     * 获得下一个ID
     *
     * @return
     */
    public static String nextId() {
        String nextId = UUID.randomUUID().toString().replace(Constant.ZX, Constant.EMPTY);
        return nextId;
    }
}
