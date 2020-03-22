package com.app.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.app.shopping")
public class ShoppingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingApiApplication.class, args);
    }

}
