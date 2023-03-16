package com.chatgpt.enums;

/**
 * 用户权益来源
 */
public enum UserRightSource {
    SYSTEM(1,"系统"),
    USER(2,"用户"),
    ;

    private int value;
    private String desc;

    UserRightSource(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String getSource(Integer value) {
        if(value == null) {
            return null;
        }
        UserRightSource[] sources = UserRightSource.values();
        for(UserRightSource source : sources) {
            if(source.getValue() == value) {
                return source.getDesc();
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
