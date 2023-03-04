package com.chatgpt.enums;

/**
 * 内容类型
 */
public enum ContentType {
    QUESTION(0 ,"问"),
    ANSWER(1 ,"答"),
    ;

    private int value;
    private String desc;

    ContentType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
