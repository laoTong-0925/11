package com.app.shopping.controller;

import com.app.shopping.cache.JedisUtil;
import com.app.shopping.mapper.TestMapper;
import com.app.shopping.service.message.MailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
@Log4j2
public class TestController {

    @Autowired
    TestMapper testMapper;

    @Autowired
    JedisUtil jedisUtil;

    @Autowired
    JedisPool jedisPool;

    @Autowired
    MailService mailService;

    @RequestMapping("/test")
    public ModelAndView test(Integer in) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello.html");
        return mv;
    }

    @RequestMapping("/indexs")
    @ResponseBody
    public String indexs() {
        return "index";
    }

    @RequestMapping("/redis-test")
    public void redisTest() {
        String test = jedisUtil.set("test", "7");
        System.out.println(test);
        Jedis resource = jedisPool.getResource();
        String tes = resource.set("tes", "1");
        System.out.println(tes);
    }

    @RequestMapping("/sql-test")
    public void mysqlTest() {
        int i = testMapper.selectCount();
        System.out.println(i);
    }

    @RequestMapping("/mail")
    public void mail() {
        mailService.send("826389503@qq.com", "hello", "?");
    }

}
