package com.app.shopping.orderToDo;

import com.app.shopping.mapper.InventoryMapper;
import com.app.shopping.mapper.OrderMapper;
import com.app.shopping.model.entity.Inventory;
import com.app.shopping.model.entity.Order;
import com.app.shopping.service.InventoryService;
import com.app.shopping.service.OrderService;
import com.app.shopping.util.Constant.SystemConstant;
import com.app.shopping.util.OrderState;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单自动取消
 */
@Component
@Log4j2
public class OrderAutoCancel implements Runnable {

    public OrderAutoCancel(OrderMapper mapper,InventoryMapper inventoryMapper) {
        this.mapper = mapper;
        this.inventoryMapper =inventoryMapper;
    }

    private OrderMapper mapper;
    private InventoryMapper inventoryMapper;

    @Override
    public void run() {
        while (true) {
            try {
                List<Order> orders = mapper.queryByState(OrderState.CREAT_NOT_PAY.getCode());
                log.info("系统查询到过期订单:{}", orders.toString());

                List<Long> collect = orders.stream().
                        filter(this::isEX).
                        map(Order::getId).collect(Collectors.toList());
                //过期的订单
                List<Order> exOrders = orders.stream().
                        filter(this::isEX).collect(Collectors.toList());
                log.info("系统自动需要取消的订单：{}", collect.toString());
                if (ListUtils.isEmpty(collect)) {
                    Thread.sleep(2000);
                    continue;
                }
                //改状态
                int i = mapper.autoCancel(collect, OrderState.AUTOCANCEL.getCode());
                log.info("取消订单执行结果--{}", i);
                //还库存
                exOrders.forEach(e ->{
                    Inventory inventory = inventoryMapper.queryByCommodityIdAndPro(e.getCommodityId(), e.getProperties());
                    int i1 = inventoryMapper.updateInventoryById(inventory.getInventory() + e.getCSum(), inventory.getId());
                    if (i1 != 0)
                        log.info("归还库存成功:{}",e.toString());
                });
            } catch (InterruptedException e) {
                log.error(e);
                e.printStackTrace();
            }

        }
    }

    private boolean isEX(Order order) {
        long now = System.currentTimeMillis();
        long exTime = order.getExTime().getTime();
        return now > exTime;
    }
}
