package com.app.shopping.model.msg;

import com.app.shopping.model.ModelObject;
import lombok.Data;

import java.util.Date;

@Data
public class SmsRecordLog extends ModelObject {

    private Long smsRecordId;
    /**
     * 返回信息
     */
    private String message;

}
