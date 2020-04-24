package com.app.shopping.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (UserImg)实体类
 *
 * @author makejava
 * @since 2020-04-12 17:27:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserImg  implements Serializable {

    private Long id;

    /**
    * user_id
    */
    private Long userId;
    /**
    * 用户头像
    */
    private String userImg;

    private Date creatTime;
    private Date updateTime;
    private Integer isDel;

    public UserImg(Long userId, String userImg) {
        this.userId = userId;
        this.userImg = userImg;
    }

    public UserImg(Long userId) {
        this.userId = userId;
    }
}