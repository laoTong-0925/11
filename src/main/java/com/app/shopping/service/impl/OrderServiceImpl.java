package com.app.shopping.service.impl;

import com.app.shopping.mapper.InventoryMapper;
import com.app.shopping.mapper.OrderMapper;
import com.app.shopping.model.User;
import com.app.shopping.model.entity.Inventory;
import com.app.shopping.model.entity.Order;
import com.app.shopping.model.entity.UserConsignee;
import com.app.shopping.service.*;
import com.app.shopping.util.Constant.SystemConstant;
import com.app.shopping.util.OrderState;
import com.app.shopping.util.Result;
import com.app.shopping.util.ResultCode;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单表(Order)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 18:29:37
 */
@Service("orderService")
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MyConsigneeService consigneeService;
    @Autowired
    private UserService userService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private UserShoppingCarService shoppingCarService;

    @Override
    @Transactional
    public Result creatOrder(int sum, long commodityId, String properties, User user, long consigneeId) {
        UserConsignee userConsignee = consigneeService.queryById((long) consigneeId);
        if (null == userConsignee)
            return Result.failed(ResultCode.SH_D_BCZ);
        //减库存
        Inventory inventory = inventoryService.queryByCommodityIdAndPro(commodityId, properties);
        if (inventory.getInventory() < sum) {
            return Result.failed(ResultCode.KC_BZ);
        }
        int now = inventory.getInventory() - sum;
        int i = inventoryMapper.updateInventoryById(now, inventory.getId());
        String nowMoney = StringUtils.isNotBlank(inventory.getActivityMoney()) ? inventory.getActivityMoney() : inventory.getMoney();
        log.info("商品活动价：{} 价格：{}", inventory.getActivityMoney(), inventory.getMoney());
        Order order = new Order(user.getId(), userConsignee.toString(), inventory.getCommodityId(), nowMoney,
                OrderState.CREAT_NOT_PAY.getCode(), 0, creatExTime(), sum, properties);
        //订单生成
        int insert = orderMapper.insert(order);

        if (insert != 0)
            return Result.success(order.getId(), ResultCode.CREAT_ORDER_S.getMessage());
        return Result.failed(ResultCode.CREAT_ORDER_F);

    }

    @Override
    public Result luckyDog(List<Long> orderIds, int state, long userId) {
        if (!ListUtils.isEmpty(orderIds)) {
            int i = orderMapper.updateStateAndPayById(state, "0", userId, orderIds);
            if (i != 0)
                return Result.success();
        }
        return Result.failed();
    }

    @Override
    public Result creatOrderByUserId(long consigneeId, User user) {

        return null;
    }

    @Override
    public List<Order> findByStateAndUser(User user, int state, int offset, int limit) {
        if (null == user)
            return new ArrayList<>();
        List<Order> byStateAndUser = orderMapper.findByStateAndUser(user.getId(), state, offset, limit);
        return byStateAndUser;
    }

    @Override
    public List<Order> findByUser(User user, int offset, int limit) {
        if (null == user)
            return new ArrayList<>();
        List<Order> byUser = orderMapper.findByUser(user.getId(), offset, limit);
        return byUser;
    }

    private Date creatExTime() {
        return new Date(System.currentTimeMillis() + SystemConstant.ORDER_EX_TIME);
    }

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
     * @param limit  查询条数
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