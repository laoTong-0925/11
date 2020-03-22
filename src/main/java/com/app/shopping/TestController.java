package com.app.shopping;

import com.app.shopping.cache.JedisUtil;
import com.app.shopping.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@RestController
public class TestController {

    @Autowired
    TestMapper testMapper;

    @Autowired
    JedisUtil jedisUtil;

    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/test")
    public ModelAndView test(Integer in) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello.html");
        return mv;
    }

    @RequestMapping("/verify")
    public ModelAndView verify(Integer in) {
        ModelAndView mv = new ModelAndView();
        TestCode tc = new TestCode();
        try {
            tc.drawImage(new FileOutputStream("d:/图片验证码.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mv.addObject("tc", tc);
        mv.setViewName("verify.html");
        return mv;
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

}
