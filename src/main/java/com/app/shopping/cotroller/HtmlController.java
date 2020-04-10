package com.app.shopping.cotroller;

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
     * 用户信息
     *
     * @return
     */
    @RequestMapping("/userInfo")
    public String userInfo() {
        return "userInfo";
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
     * 用户中心
     *
     * @return
     */
    @RequestMapping("/userCentre")
    public String userCentre() {
        return "userCentre";
    }

    /**
     * 订单
     *
     * @return
     */
    @RequestMapping("/myOrder")
    public String myOrder() {
        return "myOrder";
    }

    /**
     * 收货地址
     *
     * @return
     */
    @RequestMapping("/myConsignee")
    public String myConsignee() {
        return "myConsignee";
    }

    /**
     * 购买记录
     *
     * @return
     */
    @RequestMapping("/consumeLog")
    public String consumeLog() {
        return "consumeLog";
    }

    /**
     * 我的收藏
     *
     * @return
     */
    @RequestMapping("/collections")
    public String collections() {
        return "collections";
    }

    /**
     * 余额
     *
     * @return
     */
    @RequestMapping("/balance")
    public String balance() {
        return "balance";
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
     * 商品目录
     *
     * @return
     */
    @RequestMapping("/commodityList")
    public String commodityList() {
        return "commodityList";
    }

    /**
     * 商品管理
     *
     * @return
     */
    @RequestMapping("/commodityManage")
    public String commodityManage() {
        return "commodityManage";
    }

    /**
     * 商品发布
     *
     * @return
     */
    @RequestMapping("/commodityRelease")
    public String commodityRelease() {
        return "commodityRelease";
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
