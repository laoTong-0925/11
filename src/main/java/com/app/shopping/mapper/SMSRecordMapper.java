package com.app.shopping.mapper;

import com.app.shopping.model.msg.SmsRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SMSRecordMapper {

    int insertSMS(SmsRecord record);

    int updateStateById(long id,int state);

    int setLogIdAndSmsVIdById(long id,long logId,long vId);
}
