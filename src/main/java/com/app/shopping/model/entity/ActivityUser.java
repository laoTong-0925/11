package com.app.shopping.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 参加活动用户(ActivityUser)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityUser  implements Serializable {

    /**
    * 商品id
    */
    private Long commodityId;
    
    private Long userId;
    
    private Integer valid;
    private String consignee;
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;

    public ActivityUser(Long commodityId, Long userId, String consignee) {
        this.commodityId = commodityId;
        this.userId = userId;
        this.consignee = consignee;
    }
}