package com.app.shopping.service;

import com.app.shopping.model.entity.UserConsignee;

import java.util.List;

/**
 * (UserConsignee)表服务接口
 *
 * @author makejava
 * @since 2020-04-12 17:32:27
 */
public interface MyConsigneeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserConsignee queryById(Long id);
    List<UserConsignee> queryByUserId(Long userId);

    boolean updateAllById(Long id,String shd,String shr,String phone);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserConsignee> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userConsignee 实例对象
     * @return 实例对象
     */
    UserConsignee insert(UserConsignee userConsignee);

    /**
     * 修改数据
     *
     * @param userConsignee 实例对象
     * @return 实例对象
     */
    UserConsignee update(UserConsignee userConsignee);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}