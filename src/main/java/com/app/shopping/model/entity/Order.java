package com.app.shopping.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 订单表(Order)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    
    private Long userId;
    
    private String consignee;
    
    private Long commodityId;

    private int cSum;

    private String properties;
    
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

    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;


    public Order(Long userId, String consignee, Long commodityId, String money, Integer state, Integer valid, Date exTime,int cSum,String properties) {
        this.userId = userId;
        this.consignee = consignee;
        this.commodityId = commodityId;
        this.money = money;
        this.state = state;
        this.valid = valid;
        this.exTime = exTime;
        this.cSum = cSum;
        this.properties =properties;
    }
}