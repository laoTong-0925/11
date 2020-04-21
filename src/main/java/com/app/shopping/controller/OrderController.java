package com.app.shopping.controller;

import com.app.shopping.model.entity.Order;
import com.app.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 订单表(Order)表控制层
 *
 * @author makejava
 * @since 2020-04-21 18:29:38
 */
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Autowired
    private OrderService orderService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectOne")
    public Order selectOne(Long id) {
        return this.orderService.queryById(id);
    }

}