package com.app.shopping.model.entity;

import com.app.shopping.model.ModelObject;

import java.util.Date;
import java.io.Serializable;

/**
 * 商品表(Commodity)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:15:13
 */
public class Commodity extends ModelObject implements Serializable {
    
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


}