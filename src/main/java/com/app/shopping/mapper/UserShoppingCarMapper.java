package com.app.shopping.mapper;

import com.app.shopping.model.entity.UserShoppingCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车(UserShoppingCar)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-22 12:40:38
 */
public interface UserShoppingCarMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserShoppingCar queryById(Long id);

    List<UserShoppingCar> queryByUserId(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserShoppingCar> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userShoppingCar 实例对象
     * @return 对象列表
     */
    List<UserShoppingCar> queryAll(UserShoppingCar userShoppingCar);

    /**
     * 新增数据
     *
     * @param userShoppingCar 实例对象
     * @return 影响行数
     */
    int insert(UserShoppingCar userShoppingCar);

    /**
     * 修改数据
     *
     * @param userShoppingCar 实例对象
     * @return 影响行数
     */
    int update(UserShoppingCar userShoppingCar);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
    int deleteByUserIdAndCommodity(Long userId, Long commodityId, String commodityPro);

}