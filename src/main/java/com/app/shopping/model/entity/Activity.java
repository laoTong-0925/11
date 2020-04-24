package com.app.shopping.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 活动表(Activity)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:18:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {

    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;
    /**
    * 商品id
    */
    private Long commodityId;
    /**
    * 是否抽奖
    */
    private Integer isTicket;

    private Integer valid;

    /**
    * 开始时间
    */
    private Date startTime;
    /**
    * 结束时间
    */
    private Date endTime;
    
    private String detail;
    
    private Long inventoryId;
    /**
    * 是否降价
    */
    private Integer isActivity;

}