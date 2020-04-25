package com.app.shopping;

import com.app.shopping.mapper.InventoryMapper;
import com.app.shopping.mapper.OrderMapper;
import com.app.shopping.model.entity.Order;
import com.app.shopping.orderToDo.OrderAutoCancel;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@MapperScan(value = "com.app.shopping.mapper")
@EnableScheduling
public class ShoppingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApiApplication.class, args);
    }


}
@Component
class ListenerStartClass implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired(required = false)
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        System.out.println("****************程序已启动********************");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new OrderAutoCancel(orderMapper,inventoryMapper));
        System.out.println("start");

    }
}
