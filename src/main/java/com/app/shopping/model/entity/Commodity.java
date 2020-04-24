package com.app.shopping.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品表(Commodity)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commodity  implements Serializable {
    
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
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Date aEndTime;
    private Integer isDel;



    public Commodity(String name, String img, String detail) {
        this.name = name;
        this.img = img;
        this.detail = detail;
    }

    public Commodity(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }
}