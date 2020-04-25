package com.app.shopping.util;

import com.app.shopping.util.Constant.SystemConstant;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class DateUtil {

    /**
     * 设置过期时间 5分钟 300000mm
     *
     * @param paramDate 参数时间
     * @return
     */
    public static Date setExpireTime(Date paramDate) {
        long time = paramDate.getTime();
        Date date = new Date(time + SystemConstant.EXPIRETIME);
        return date;
    }

    /**
     *
     * @return
     */
    public static Date getAStartTime() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.set(Calendar.HOUR, 20);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        date = c.getTime();
        return date;
    }

    /**
     * 测试用
     * @return
     */
    public static Date getAStartTimeTest() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.DATE, 1); //日期加1天
        c.set(Calendar.HOUR, 20);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        date = c.getTime();
        return date;
    }

    public static void main(String[] args) {
        Date aStartTimeTest = getAStartTimeTest();
        System.out.println();
    }

    /**
     * 生成活动结束时间 时间是一天
     *
     * @return
     */
    public static Date setActivityExTIme() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.DATE, 1); //日期加1天
        c.set(Calendar.HOUR, 20);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        date = c.getTime();
        return date;
    }
}
