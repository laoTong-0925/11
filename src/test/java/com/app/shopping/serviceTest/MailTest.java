package com.app.shopping.serviceTest;

import com.app.shopping.ShoppingApiApplication;
import com.app.shopping.service.message.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ShoppingApiApplication.class)
public class MailTest {

    @Autowired
    MailService mailService;

    @Test
    public void testMail(){
        mailService.send("826389503@qq.com","hello","?");
    }
}
