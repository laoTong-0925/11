package com.app.shopping.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 消费记录(ConsumeLog)实体类
 *
 * @author makejava
 * @since 2020-04-21 18:15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumeLog  implements Serializable {

    /**
    * user_id
    */
    private Long userId;
    
    private Long orderId;
    
    private String money;
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;

    public ConsumeLog(Long userId, Long orderId, String money) {
        this.userId = userId;
        this.orderId = orderId;
        this.money = money;
    }
}