package com.chatgpt.enums;

/**
 * 用户类型
 */
public enum UserType {
    NORMAL(0,"普通用户"),
    VIP(1 ,"VIP"),
    ;

    private int value;
    private String desc;

    UserType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String getUserType(Integer value) {
        if(value == null) {
            return null;
        }
        UserType[] userTypes = UserType.values();
        for(UserType ut : userTypes) {
            if(ut.getValue() == value) {
                return ut.getDesc();
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
