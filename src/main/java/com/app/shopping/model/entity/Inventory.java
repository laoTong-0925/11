package com.app.shopping.model.entity;

import com.app.shopping.model.ModelObject;

import java.util.Date;
import java.io.Serializable;

/**
 * 库存表(Inventory)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:15:13
 */
public class Inventory extends ModelObject implements Serializable {
    
    private Long commodityId;
    
    private String property;
    
    private Integer inventory;
    
    private String money;
    
    private String activityMoney;
    
    private String moneyLog;

}