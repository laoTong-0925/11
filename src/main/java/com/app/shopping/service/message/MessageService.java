package com.app.shopping.service.message;

import com.app.shopping.util.Result;

/**
 * 消息服务
 */
public interface MessageService {
    Result send(String to, String subject, String text);
    Result send(String to, String text, long date);
}
