package com.app.shopping.model.entity;

import com.app.shopping.model.ModelObject;

import java.util.Date;
import java.io.Serializable;

/**
 * 参加活动用户(ActivityUser)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:15:13
 */
public class ActivityUser extends ModelObject implements Serializable {

    /**
    * 活动id
    */
    private Long activityId;
    
    private Long userId;
    
    private Integer valid;

}