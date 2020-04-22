package com.app.shopping.controller;

import com.app.shopping.model.entity.UserInfo;
import com.app.shopping.service.UserInfoService;
import com.app.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户信息，余额(UserInfo)表控制层
 *
 * @author makejava
 * @since 2020-04-21 20:27:32
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    /**
     * 服务对象
     */
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/selectOne")
    public UserInfo selectOne(Integer id) {
        return this.userInfoService.queryById(Long.valueOf(id));
    }




}