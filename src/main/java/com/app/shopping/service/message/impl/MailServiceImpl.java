package com.app.shopping.service.message.impl;

import com.app.shopping.service.message.MailService;
import com.app.shopping.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    //邮件发件人
    @Value("${mail.fromMail.addr}")
    private String from;
//
//    @Autowired
//    TemplateEngine templateEngine;

    @Override
    public Result send(String to, String subject, String text) {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("verifyCode", text);
        //将模块引擎内容解析成html字符串
//        String emailContent = templateEngine.process("emailTemplate", context);
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            mailSender.send(message);
//            return CommonResult.success("发送成功");
            //logger.info("简单邮件已经发送。");
        } catch (Exception e) {
//            return CommonResult.failed("发送失败");
//            logger.error("发送简单邮件时发生异常！", e);
        }
        return null;
    }

    @Override
    public Result send(String to, String text, long date) {
        return null;
    }


}
