package com.app.shopping.model.vo;

import lombok.Data;

/**
 *
 */
@Data
public class ShoppingCarVo {

    /**
     * 图片地址
     */
    private String imgPth;

    /**
     * 名称
     */
    private String commodityName;

    /**
     * 价格
     */
    private String commodityMoney;

    /**
     * 活动价
     */
    private String commodityAMoney;
    /**
     * 数量
     */
    private int sum;
    /**
     * 总价
     */
    private String sumMoney;


    public ShoppingCarVo(String imgPth, String commodityName, String commodityMoney, int sum, String commodityAMoney1, String sumMoney) {
        this.imgPth = imgPth;
        this.commodityName = commodityName;
        this.commodityMoney = commodityMoney;
        this.sum = sum;
        this.commodityAMoney = commodityAMoney1;
        this.sumMoney = sumMoney;
    }
}
