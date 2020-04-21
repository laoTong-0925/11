package com.app.shopping.model.entity;

import com.app.shopping.model.ModelObject;

import java.util.Date;
import java.io.Serializable;

/**
 * 消费记录(ConsumeLog)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:15:13
 */
public class ConsumeLog extends ModelObject implements Serializable {

    /**
    * user_id
    */
    private Long userId;
    
    private Long orderId;
    
    private String money;

}