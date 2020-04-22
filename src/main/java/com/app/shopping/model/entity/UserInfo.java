package com.app.shopping.model.entity;
import com.app.shopping.model.ModelObject;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserInfo)实体类
 *
 * @author makejava
 * @since 2020-04-12 17:23:48
 */
@Data
public class UserInfo extends ModelObject implements Serializable {

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

    public UserInfo(Long userId, String payPass) {
        this.userId = userId;
        this.payPass = payPass;
    }

}