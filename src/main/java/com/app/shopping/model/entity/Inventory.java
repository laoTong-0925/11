package com.app.shopping.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存表(Inventory)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory implements Serializable {

    private Long commodityId;

    private String property;

    private Integer inventory;

    private String money;

    private String activityMoney;

    private String moneyLog;
    private int isTicket;
    private int isActivity;

    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;

    public Inventory(Long commodityId, String property, Integer inventory, String money) {
        this.commodityId = commodityId;
        this.property = property;
        this.inventory = inventory;
        this.money = money;
    }
}