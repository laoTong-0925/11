package com.app.shopping.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserInfo)实体类
 *
 * @author makejava
 * @since 2020-04-12 17:23:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

    /**
    * user_id
    */
    private Long userId;
    /**
    * 余额
    */
    private String balance;
    /**
    * 积分
    */
    private Integer integral;
    /**
     * 支付密码
     */
    private String payPass;
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;

    public UserInfo(Long userId, String payPass) {
        this.userId = userId;
        this.payPass = payPass;
    }

}