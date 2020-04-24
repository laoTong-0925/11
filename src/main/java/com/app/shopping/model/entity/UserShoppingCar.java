package com.app.shopping.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物车(UserShoppingCar)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:20:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShoppingCar implements Serializable {
    
    private Long userId;
    
    private Long commodityId;
    
    private String commodityPro;
    
    private Integer sum;
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;

    public UserShoppingCar(Long userId, Long commodityId, String commodityPro, Integer sum) {
        this.userId = userId;
        this.commodityId = commodityId;
        this.commodityPro = commodityPro;
        this.sum = sum;
    }
}