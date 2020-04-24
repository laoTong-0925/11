package com.app.shopping.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCarVo {
    private long shoppingCarId;

    /**
     * 图片地址
     */
    private String imgPth;

    /**
     * 名称
     */
    private String commodityName;

    private String commodityPro;

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

}
