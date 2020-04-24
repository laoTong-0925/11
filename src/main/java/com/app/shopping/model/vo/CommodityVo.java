package com.app.shopping.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityVo {

    private Long commodityId;

    private String pro;

    private int inventory;

    private String name;

    private String img;

    private String detail;

    /**
     * 0 不知1衣服 2裤子 3鞋子 4海鲜肉类 5蔬菜 6零食 7酒水 8生活用品 9其他
     */
    private Integer type;

    /**
     * 是否抽奖 0无 1是
     */
    private Integer isTicket;
    /**
     * 是否活动 0否 1是
     */
    private Integer isActivity;

    private String money;

    private String aMoney;

    private Date creatTime;
    private Date updateTime;

    public CommodityVo(Long commodityId, String pro, int inventory,
                       String name, Integer type, Integer isTicket,
                       Integer isActivity, String money, String aMoney) {
        this.commodityId = commodityId;
        this.pro = pro;
        this.inventory = inventory;
        this.name = name;
        this.type = type;
        this.isTicket = isTicket;
        this.isActivity = isActivity;
        this.money = money;
        this.aMoney = aMoney;
    }

}
