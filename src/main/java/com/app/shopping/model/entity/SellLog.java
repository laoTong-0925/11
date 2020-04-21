package com.app.shopping.model.entity;

import com.app.shopping.model.ModelObject;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 销量表(SellLog)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:13:02
 */
@Data
public class SellLog extends ModelObject implements Serializable {

    private Long commodityId;
    
    private String money;
    
    private String oneMoney;
    
    private Long sum;

    /**
    * 商品属性
    */
    private String property;


}