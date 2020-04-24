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

    public static Date setActivityExTIme() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);   //设置当前日期
        c.add(Calendar.DATE, 1); //日期加1天
        c.set(Calendar.HOUR,28);//加28是因为数据库 8小时差的问题
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.MILLISECOND,0);
        date = c.getTime();
        return date;
    }
}
