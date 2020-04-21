package com.app.shopping.controller;

import com.app.shopping.model.entity.Activity;
import com.app.shopping.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 活动表(Activity)表控制层
 *
 * @author makejava
 * @since 2020-04-21 18:25:47
 */
@RestController
@RequestMapping("activity")
public class ActivityController {
    /**
     * 服务对象
     */
    @Autowired
    private ActivityService activityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectOne")
    public Activity selectOne(Long id) {
        return this.activityService.queryById(id);
    }

}