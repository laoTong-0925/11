package com.app.shopping.model.entity;

import com.app.shopping.model.ModelObject;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserConsignee)实体类
 *
 * @author makejava
 * @since 2020-04-12 17:29:02
 */
public class UserConsignee extends ModelObject implements Serializable {
    private static final long serialVersionUID = 331732254706701273L;
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


}