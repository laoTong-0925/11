package com.app.shopping.model.msg;

import com.app.shopping.model.ModelObject;
import lombok.Data;

import java.util.Date;

@Data
public class SmsVCode extends ModelObject {

    private Long smsRecordId;

    private String code;

    private Date businessTime;

    private Date expireTime;
    /**
     * 状态 0未验证 1已验证
     */
    private Integer state;

}
