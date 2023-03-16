package com.chatgpt.enums;

/**
 * 支付状态
 */
public enum PayStatus {
    TO_PAY(1,"待支付"),
    PAYED(2,"已支付"),
    CANCELED(3,"已取消"),
    ;

    private int value;
    private String desc;

    PayStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static String getPayStatus(Integer value) {
        if(value == null) {
            return null;
        }
        PayStatus[] pss = PayStatus.values();
        for(PayStatus ps : pss) {
            if(ps.getValue() == value) {
                return ps.getDesc();
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
