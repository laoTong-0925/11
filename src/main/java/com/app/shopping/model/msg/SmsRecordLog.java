package com.app.shopping.model.msg;

import lombok.Data;

import java.util.Date;

@Data
public class SmsRecordLog  {

    private Long smsRecordId;
    /**
     * 返回信息
     */
    private String message;
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;

}
