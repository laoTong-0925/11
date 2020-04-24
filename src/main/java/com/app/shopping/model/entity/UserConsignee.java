package com.app.shopping.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserConsignee)实体类
 *
 * @author makejava
 * @since 2020-04-12 17:29:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserConsignee  implements Serializable {
    /**
    * user_id
    */
    private Long userId;
    /**
    * 收货地址
    */
    private String consignee;
    /**
    * 收货人
    */
    private String consigneeMan;
    /**
    * 收货电话
    */
    private String phone;
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;

    public UserConsignee(Long userId, String consignee, String consigneeMan, String phone) {
        this.userId = userId;
        this.consignee = consignee;
        this.consigneeMan = consigneeMan;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return
                "地址=" + consignee + "《》" +
                ", 收货人=" + consigneeMan + "《》" +
                ", 电话=" + phone ;
    }
}