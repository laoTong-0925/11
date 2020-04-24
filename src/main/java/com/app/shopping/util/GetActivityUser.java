package com.app.shopping.util;

import com.app.shopping.mapper.ActivityUserMapper;
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
    ActivityUserMapper activityUserMapper;

    @Scheduled(cron = "0 0 20 * * ?")
    public void  getOne(){
//todo 中奖选人 发短信
    }

    //在固定时间执行
    @Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron(){
        System.out.println ("------------------定时任务-----------");
    }

}
