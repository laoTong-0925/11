package com.app.shopping.model.entity;

import com.app.shopping.model.ModelObject;

import java.util.Date;
import java.io.Serializable;

/**
 * 订单表(Order)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:15:13
 */
public class Order extends ModelObject implements Serializable {
    
    private Long userId;
    
    private String consignee;
    
    private Long commodityId;
    
    private String money;
    /**
    * 0新建 1下单未支付 2下单支付 3已发货 4完成 9取消
    */
    private Integer state;
    
    private Integer valid;

    /**
    * 过期时间 加30分钟
    */
    private Date exTime;
    
    private Integer isTicket;
    
    private String pay;


}