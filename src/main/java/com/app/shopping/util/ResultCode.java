package com.app.shopping.util;

public enum ResultCode {
    SUCCESS(200, "成功"),
    FAILED(400, "失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),

    IMG_VERIFY_SUCCESS(2001010,"验证码正确"),
    IMG_VERIFY_FAILED(2001011,"验证码错误"),

    USER_IS_EXIST(2001020,"用户名已存在"),
    USER_AVAILABLE(2001021,"用户名可用"),

    PHONE_IS_EXIST(2001030,"手机号已被注册"),
    PHONE_AVAILABLE(2001031,"手机号可用"),

    SMS_SEND_SUCCESS(2002000,"验证短信发送成功"),
    SMS_SEND_FAILED(2002001,"验证短信发送失败，请稍后再尝试");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
