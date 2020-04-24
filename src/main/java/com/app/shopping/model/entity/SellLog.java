package com.app.shopping.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 销量表(SellLog)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:13:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellLog implements Serializable {

    private Long commodityId;
    
    private String money;
    
    private String oneMoney;
    
    private Long sum;

    /**
    * 商品属性
    */
    private String property;
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;


}