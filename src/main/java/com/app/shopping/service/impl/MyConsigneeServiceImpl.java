package com.app.shopping.service.impl;

import com.app.shopping.mapper.MyConsigneeMapper;
import com.app.shopping.model.entity.UserConsignee;
import com.app.shopping.service.MyConsigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * (UserConsignee)表服务实现类
 *
 * @author makejava
 * @since 2020-04-12 17:32:27
 */
@Service("userConsigneeService")
public class MyConsigneeServiceImpl implements MyConsigneeService {
    @Autowired
    private MyConsigneeMapper myConsigneeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserConsignee queryById(Long id) {
        return this.myConsigneeMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserConsignee> queryAllByLimit(int offset, int limit) {
        return this.myConsigneeMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userConsignee 实例对象
     * @return 实例对象
     */
    @Override
    public UserConsignee insert(UserConsignee userConsignee) {
        this.myConsigneeMapper.insert(userConsignee);
        return userConsignee;
    }

    /**
     * 修改数据
     *
     * @param userConsignee 实例对象
     * @return 实例对象
     */
    @Override
    public UserConsignee update(UserConsignee userConsignee) {
        this.myConsigneeMapper.update(userConsignee);
        return this.queryById(userConsignee.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.myConsigneeMapper.deleteById(id) > 0;
    }
}