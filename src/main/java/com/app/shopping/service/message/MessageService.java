package com.app.shopping.service.message;

/**
 * 消息服务
 */
public interface MessageService {
    void send(String to, String subject, String text);
}
