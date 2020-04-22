package com.app.shopping.orderToDo;

import com.app.shopping.model.entity.Order;
import com.app.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 */
@Component
public class OrderAutoCancel implements Runnable{

    @Autowired
    OrderService orderService;

    @Override
    public void run() {
        while (true){
            List<Order> orders = orderService.queryByState(1);
            orders.stream();
        }
    }


}
