package com.app.shopping.util;

public enum Sms {
    
    /**
     * 短信类型 1验证码 2普通
     */
    SMS_v(1,"验证码"),
    SMS_NOMEL(2,"普通短信"),
    /**
     * 短信state
     */
    SMS_NEW(0,"新建"),
    SMS_ING(1,"正在发送"),
    SMS_SU(2,"发送成功"),
    SMS_FI(3,"发送失败");

    private int code;
    private String message;

    Sms(int code, String message) {
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
