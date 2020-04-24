package com.app.shopping.service;

import com.app.shopping.model.entity.Inventory;

import java.util.List;

/**
 * 库存表(Inventory)表服务接口
 *
 * @author makejava
 * @since 2020-04-21 18:29:28
 */
public interface InventoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Inventory queryById(Long id);

    Inventory queryByCommodityIdAndPro(Long cId, String property);


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Inventory> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    Inventory insert(Inventory inventory);

    /**
     * 修改数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    Inventory update(Inventory inventory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}