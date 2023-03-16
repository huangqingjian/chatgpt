package com.chatgpt.enums;

/**
 * 会员有效期
 */
public enum VipPeriod {
    WEEK(1,"周"),
    MONTH(2,"月"),
    YEAR(3,"年"),
    ;

    private int value;
    private String desc;

    VipPeriod(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String getProductPeriod(Integer value) {
        if(value == null) {
            return null;
        }
        VipPeriod[] vps = VipPeriod.values();
        for(VipPeriod vp : vps) {
            if(vp.getValue() == value) {
                return vp.getDesc();
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
