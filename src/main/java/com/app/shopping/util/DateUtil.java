package com.app.shopping.util;

import com.app.shopping.util.Constant.SystemConstant;
import org.springframework.stereotype.Component;

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
        return new Date(time + SystemConstant.EXPIRETIME);
    }
}
