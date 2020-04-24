package com.app.shopping.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/html")
@Log4j2
public class HtmlController {

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String verify() {
        return "register";
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }



    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    /**
     * 头页面
     *
     * @return
     */
    @RequestMapping("/head")
    public String head() {
        return "head";
    }

    /**
     * 尾页面
     */
    @RequestMapping("/footer")
    public String footer() {
        return "footer";
    }



    /**
     * 发货管理
     *
     * @return
     */
    @RequestMapping("/DeliveryManagement")
    public String deliveryManagement() {
        return "DeliveryManagement";
    }

    /**
     * 收款
     *
     * @return
     */
    @RequestMapping("/gathering")
    public String gathering() {
        return "gathering";
    }

}
