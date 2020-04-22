package com.app.shopping;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
class ListenerStartClass implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired(required = false)
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        log.info("开机自启动");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        executorService.submit()
    }
}