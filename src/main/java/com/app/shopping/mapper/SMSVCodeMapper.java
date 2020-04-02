package com.app.shopping.mapper;

import com.app.shopping.model.msg.SmsVCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SMSVCodeMapper {
    int insert(SmsVCode smsVCode);

    int setRecordIdById(long id ,long recordId);
}
