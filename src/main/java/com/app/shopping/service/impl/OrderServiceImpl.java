package com.app.shopping.service.impl;

import com.app.shopping.model.entity.Order;
import com.app.shopping.mapper.OrderMapper;
import com.app.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

/**
 * 订单表(Order)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 18:29:37
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Order queryById(Long id) {
        return this.orderMapper.queryById(id);
    }

    @Override
    public List<Order> queryByState(int state) {
        List<Order> orders = orderMapper.queryByState(state);
        return orders;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Order> queryAllByLimit(int offset, int limit) {
        return this.orderMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order insert(Order order) {
        this.orderMapper.insert(order);
        return order;
    }

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 实例对象
     */
    @Override
    public Order update(Order order) {
        this.orderMapper.update(order);
        return this.queryById(order.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.orderMapper.deleteById(id) > 0;
    }
}