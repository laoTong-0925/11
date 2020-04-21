package com.app.shopping.model.entity;

import com.app.shopping.model.ModelObject;

import java.util.Date;
import java.io.Serializable;

/**
 * 购物车(UserShoppingCar)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:20:32
 */
public class UserShoppingCar extends ModelObject implements Serializable {
    
    private Long userId;
    
    private Long commodityId;
    
    private String commodityPro;
    
    private Integer sum;

}