package com.app.shopping.util.Constant;

public interface SystemConstant {

    /**
     * 短信 常量参数
     */
    String ALIBB_REGIONID = "cn-hangzhou";
    String ALIBB_ACCESSKEYID = "LTAI4FuZJ11ees7Mf1TEns4m";
    String ALIBB_SECRET = "IMHPxyBkSw0LuSnJpJm7jEyc7dsNyD";
    String ALIBB_DOMAIN = "dysmsapi.aliyuncs.com";
    String ALIBB_VERSION = "2017-05-25";
    String ALIBB_ACTION = "SendSms";
    String ALIBB_KEY_REGIONID = "RegionId";
    String ALIBB_KEY_PHONE_NUM = "PhoneNumbers";
    String ALIBB_KEY_SIGN_NAME = "SignName";
    String ALIBB_KEY_TEMPLATE_CODE = "TemplateCode";
    String ALIBB_KEY_TEMPLATE_PARAM = "TemplateParam";
    /**
     * 短信签名
     */
    String ALIBB_SIGN_VAL = "虾pin";
    /**
     * 验证码短信模板
     */
    String ALIBB_KEY_TEMPLATE_CODE_VAL = "SMS_186952124";
    /**
     * 过期时间 加数 300000毫秒
     */
    Integer EXPIRETIME = 300000 ;
    /**
     * Redis验证码标识
     */
    String REDISVTAG = "REDISVTAG";
    /**
     * Redis验证码数据库
     */
    Integer REDISVTAG_DATAINDEX = 0;
    /**
     * 订单过期时间
     */
    Integer ORDER_EX_TIME = 1800000 ;
}
