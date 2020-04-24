package com.app.shopping.util;

public enum OrderState {
    NEW(0,"新建"),
    CREAT_NOT_PAY(1,"下单未支付"),
    CREAT_PAY(2,"下单已支付"),
    DELIVERY(3,"发货"),
    END(4,"完成"),

    CANCEL(8,"手动取消"),
    AUTOCANCEL(9,"自动取消");


    private int code;
    private String message;

    OrderState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
