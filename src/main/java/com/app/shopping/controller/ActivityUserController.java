package com.app.shopping.controller;

import com.app.shopping.model.entity.ActivityUser;
import com.app.shopping.service.ActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 参加活动用户(ActivityUser)表控制层
 *
 * @author makejava
 * @since 2020-04-21 18:29:13
 */
@RestController
@RequestMapping("activityUser")
public class ActivityUserController {
    /**
     * 服务对象
     */
    @Autowired
    private ActivityUserService activityUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectOne")
    public ActivityUser selectOne(Long id) {
        return this.activityUserService.queryById(id);
    }

}