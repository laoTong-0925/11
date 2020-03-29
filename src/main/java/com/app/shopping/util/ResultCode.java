package com.app.shopping.util;

public enum ResultCode {
    SUCCESS(200, "成功"),
    FAILED(400, "失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),

    IMG_VERIFY_SUCCESS(2001000,"验证码正确"),
    IMG_VERIFY_FAILED(2001001,"验证码错误");

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
