package com.app.shopping.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo {
    private Long orderId;

    private Long userId;

    /**
     * 收货地址
     */
    private String consignee;
    private String img;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 数量
     */
    private int cSum;

    /**
     * 商品属性
     */
    private String properties;
    /**
     * 商品名称
     */
    private String commodityName;

    private String money;
    private String oneMoney;
    /**
     * NEW(0,"新建"),
     * CREAT_NOT_PAY(1,"下单未支付"),
     * CREAT_PAY(2,"下单已支付"),
     * DELIVERY(3,"发货"),
     * END(4,"完成"),
     * <p>
     * CANCEL(8,"手动取消"),
     * AUTOCANCEL(9,"自动取消");
     */
    private Integer state;

    /**
     * 过期时间 加30分钟
     */
    private Date exTime;

    /**
     * 是否中奖
     */
    private Integer isTicket;

    private String pay;
    private Date creatTime;
    private Date updateTime;

    public OrderVo(Long orderId, Long userId, String consignee,
                   Long commodityId, int cSum, String properties,
                   String commodityName, String money, Integer state,
                   Date exTime, Integer isTicket, String pay,
                   Date creatTime, Date updateTime, String oneMoney, String img) {
        this.orderId = orderId;
        this.userId = userId;
        this.consignee = consignee;
        this.commodityId = commodityId;
        this.cSum = cSum;
        this.properties = properties;
        this.commodityName = commodityName;
        this.money = money;
        this.state = state;
        this.exTime = exTime;
        this.isTicket = isTicket;
        this.pay = pay;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
        this.oneMoney = oneMoney;
        this.img = img;
    }
}
