package com.app.shopping.service;

import com.app.shopping.model.entity.UserShoppingCar;

import java.util.List;

/**
 * 购物车(UserShoppingCar)表服务接口
 *
 * @author makejava
 * @since 2020-04-22 12:40:41
 */
public interface UserShoppingCarService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserShoppingCar queryById(Long id);

    List<UserShoppingCar> queryByUserId(Long UserId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserShoppingCar> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userShoppingCar 实例对象
     * @return 实例对象
     */
    UserShoppingCar insert(UserShoppingCar userShoppingCar);

    /**
     * 修改数据
     *
     * @param userShoppingCar 实例对象
     * @return 实例对象
     */
    UserShoppingCar update(UserShoppingCar userShoppingCar);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
    boolean deleteByUserIdAndCommodity(Long userId,Long commodityId,String commodityPro);

}