package com.app.shopping.util;

import com.app.shopping.service.GetActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class GetActivityUser {

    @Autowired
    private GetActivityUserService getActivityUserService;

    @Scheduled(cron = "0 0 20 * * ?")
    public void getOne() {
        System.out.println("------------------定时任务正式抽奖-----------");

        getActivityUserService.getActivityUserByEndTime();
    }

    //在固定时间执行
    @Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron() {
        System.out.println("------------------定时任务-----------");
    }

    @Scheduled(cron = "0 */1 *  * * * ")
    public void test() {
//        getActivityUserService.getActivityUserByEndTime();
        System.out.println("------------------定时任务测试抽奖-----------");

    }

}
