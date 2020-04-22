package com.app.shopping.model.entity;

import com.app.shopping.model.ModelObject;
import lombok.Data;

import java.io.Serializable;

/**
 * (UserImg)实体类
 *
 * @author makejava
 * @since 2020-04-12 17:27:28
 */
@Data
public class UserImg extends ModelObject implements Serializable {

    /**
    * user_id
    */
    private Long userId;
    /**
    * 用户头像
    */
    private String userImg;

    public UserImg(Long userId, String userImg) {
        this.userId = userId;
        this.userImg = userImg;
    }

    public UserImg(Long userId) {
        this.userId = userId;
    }
}