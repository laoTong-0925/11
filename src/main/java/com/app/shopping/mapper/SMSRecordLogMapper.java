package com.app.shopping.mapper;

import com.app.shopping.model.msg.SmsRecordLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SMSRecordLogMapper {
    int insert(SmsRecordLog smsRecordLog);
    int setMessageById(long id,String message);
    int setRecordIdById(long id,long recordId);
}
