package com.app.shopping.model.msg;

import lombok.Data;

import java.util.Date;

@Data
public class SmsRecord {

    private String toNumber;

    private Long logRecordId;

    private Long smsvRecordId;
    /**
     * 类型 1验证码 2普通
     */
    private Integer type;

    private Date businessTime;
    /**
     * 短信状态 0新建 1发送中 2成功 3失败
     */
    private Integer state;
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;

    public void build(String toNumber, long logRecordId,
                      long smsvRecordId, int type, Date businessTime) {
        this.setBusinessTime(businessTime);
        this.setLogRecordId(logRecordId);
        this.setToNumber(toNumber);
        this.setType(type);
        this.setSmsvRecordId(smsvRecordId);
    }

    public void build(String toNumber, int type, Date businessTime) {
        this.setBusinessTime(businessTime);
        this.setToNumber(toNumber);
        this.setType(type);
    }

}
