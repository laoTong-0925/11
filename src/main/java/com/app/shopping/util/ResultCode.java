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
    SMS_SEND_FAILED(2002001,"验证短信发送失败，请稍后再尝试"),

    CREAT_ORDER_F(6001,"下单失败"),
    CREAT_ORDER_S(6002,"下单成功"),

    KC_BZ(3001000,"库存不足"),
    SP_BCZ(3001001,"商品不存在"),
    SH_D_BCZ(3001002,"收货地不存在"),

    JR_GWC_S(3001003,"添加购物车成功"),
    JR_GWC_F(3001004,"添加购物车失败"),
    YC_GWC_S(3001005,"移除购物车成功"),
    YC_GWC_F(3001006,"移除购物车失败"),

    PAY_PASS_F(7001,"支付失败"),
    PAY_PASS_S(7002,"支付成功");


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
