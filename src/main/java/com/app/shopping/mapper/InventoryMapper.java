package com.app.shopping.mapper;

import com.app.shopping.model.entity.Inventory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存表(Inventory)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-21 18:29:27
 */
public interface InventoryMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Inventory queryById(Long id);

    int updateIsTByCId(Long cId,int isT);

    List<Inventory> queryByCId(Long cId);

    Inventory queryByCommodityIdAndPro(Long cId, String property);

    int updateInventoryById(int inventory, Long id);
    int updateInventoryByCId(int inventory, Long cId);

    int updateInventoryAndMoneyByCIdAndPro(int inventory, String money, Long cId, String pro);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Inventory> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param inventory 实例对象
     * @return 对象列表
     */
//    List<Inventory> queryAll(Inventory inventory);
    List<Inventory> queryAll();

    /**
     * 新增数据
     *
     * @param inventory 实例对象
     * @return 影响行数
     */
    int insert(Inventory inventory);

    int insertBatch(@Param("list") List<Inventory> inventory);

    /**
     * 修改数据
     *
     * @param inventory 实例对象
     * @return 影响行数
     */
    int update(Inventory inventory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
    int removeByCId(Long id);

}