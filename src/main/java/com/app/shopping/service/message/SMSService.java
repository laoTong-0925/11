package com.app.shopping.service.message;

/**
 * 短信服务
 */
public interface SMSService extends MessageService {

    boolean vCodeIsExpire(String phone,String code);
}
