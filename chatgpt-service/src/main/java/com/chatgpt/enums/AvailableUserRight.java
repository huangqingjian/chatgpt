package com.chatgpt.enums;

/**
 * 可用用户权益
 */
public enum AvailableUserRight {
    TIMES(1,"次数"),
    PERIOD(2,"时间段"),
    ;

    private int value;
    private String desc;

    AvailableUserRight(int value, String desc) {
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
