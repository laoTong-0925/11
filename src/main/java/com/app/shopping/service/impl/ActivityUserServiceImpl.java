package com.app.shopping.service.impl;

import com.app.shopping.mapper.ActivityUserMapper;
import com.app.shopping.model.entity.ActivityUser;
import com.app.shopping.service.ActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 参加活动用户(ActivityUser)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 18:29:13
 */
@Service("activityUserService")
public class ActivityUserServiceImpl implements ActivityUserService {
    @Autowired
    private ActivityUserMapper activityUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ActivityUser queryById(Long id) {
        return this.activityUserMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ActivityUser> queryAllByLimit(int offset, int limit) {
        return this.activityUserMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param activityUser 实例对象
     * @return 实例对象
     */
    @Override
    public ActivityUser insert(ActivityUser activityUser) {
        this.activityUserMapper.insert(activityUser);
        return activityUser;
    }

    /**
     * 修改数据
     *
     * @param activityUser 实例对象
     * @return 实例对象
     */
    @Override
    public ActivityUser update(ActivityUser activityUser) {
        this.activityUserMapper.update(activityUser);
        return this.queryById(activityUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.activityUserMapper.deleteById(id) > 0;
    }
}