package com.chatgpt.enums;

/**
 * 是否状态
 */
public enum YesNo {
    NO(0 ,"否"),
    YES(1, "是")
    ;

    private int value;
    private String desc;

    YesNo(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String getYesNoDesc(Integer value) {
        if(value == null) {
            return null;
        }
        YesNo[] yesNos = YesNo.values();
        for(YesNo s : yesNos) {
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
