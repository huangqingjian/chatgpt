package com.chatgpt.enums;

/**
 * 性别
 */
public enum Sex {
    UNKNOW(0 ,"未知"),
    MALE(1 ,"男"),
    FEMALE(2, "女")
    ;

    private int value;
    private String desc;

    Sex(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String getSexDesc(Integer value) {
        if(value == null) {
            return null;
        }
        Sex[] sexes = Sex.values();
        for(Sex s : sexes) {
            if(s.getValue() == value) {
                return s.getDesc();
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
