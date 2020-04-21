package com.app.shopping.service.impl;

import com.app.shopping.model.entity.Inventory;
import com.app.shopping.mapper.InventoryMapper;
import com.app.shopping.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 库存表(Inventory)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 18:29:29
 */
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Inventory queryById(Long id) {
        return this.inventoryMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Inventory> queryAllByLimit(int offset, int limit) {
        return this.inventoryMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    @Override
    public Inventory insert(Inventory inventory) {
        this.inventoryMapper.insert(inventory);
        return inventory;
    }

    /**
     * 修改数据
     *
     * @param inventory 实例对象
     * @return 实例对象
     */
    @Override
    public Inventory update(Inventory inventory) {
        this.inventoryMapper.update(inventory);
        return this.queryById(inventory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.inventoryMapper.deleteById(id) > 0;
    }
}