package com.app.shopping.model.entity;

import com.app.shopping.model.ModelObject;

import java.util.Date;
import java.io.Serializable;

/**
 * 活动表(Activity)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:18:59
 */
public class Activity extends ModelObject implements Serializable {

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