package com.app.shopping.service;

import com.app.shopping.model.User;
import com.app.shopping.model.entity.Order;
import com.app.shopping.util.Result;

import java.util.List;

/**
 * 订单表(Order)表服务接口
 *
 * @author makejava
 * @since 2020-04-21 18:29:36
 */
public interface OrderService {

    /**
     * 下单操作
     */
    Result creatOrder(int sum, long commodityId, String properties, User user, long consigneeId);

    Result creatOrderByUserId(long consigneeId,User user);

    List<Order> findByStateAndUser(User user,int state, int offset, int limit);
    List<Order> findByUser(User user, int offset, int limit);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Order queryById(Long id);

    List<Order> queryByState(int state);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Order> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    Order insert(Order order);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    Order update(Order order);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}