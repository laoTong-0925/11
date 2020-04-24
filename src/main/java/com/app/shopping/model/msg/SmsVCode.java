package com.app.shopping.model.msg;

import lombok.Data;

import java.util.Date;

@Data
public class SmsVCode  {

    private Long smsRecordId;

    private String code;

    private Date businessTime;

    private Date expireTime;
    /**
     * 状态 0未验证 1已验证
     */
    private Integer state;
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;

}
