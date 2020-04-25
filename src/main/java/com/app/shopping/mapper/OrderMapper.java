package com.app.shopping.mapper;

import com.app.shopping.model.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单表(Order)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-21 18:29:34
 */
public interface OrderMapper {

    int autoCancel(List<Long> ids, int state);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Order queryById(Long id);

    List<Order> queryByState(int state);

    List<Order> queryByStateAndId(int state, List<Long> ids);

    int updateStateAndPayByUserId(int state, String pay, long userId, List<Long> ids);

    int remove(long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Order> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);
    int queryCountByUserId(long userId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param order 实例对象
     * @return 对象列表
     */
    List<Order> queryAll(Order order);

    /**
     * 新增数据
     *
     * @param order 实例对象
     * @return 影响行数
     */
    int insert(Order order);

    List<Order> findByStateAndUser(long userId, int state, int offset, int limit);

    List<Order> findByUser(long userId, int offset, int limit);

    /**
     * 修改数据
     *
     * @param order 实例对象
     * @return 影响行数
     */
    int update(Order order);
    int updateStateById(long id,int state);



    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}