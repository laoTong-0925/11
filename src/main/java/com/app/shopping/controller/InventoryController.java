package com.app.shopping.controller;

import com.app.shopping.model.entity.Inventory;
import com.app.shopping.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 库存表(Inventory)表控制层
 *
 * @author makejava
 * @since 2020-04-21 18:29:30
 */
@RestController
@RequestMapping("inventory")
public class InventoryController {
    /**
     * 服务对象
     */
    @Autowired
    private InventoryService inventoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectOne")
    public Inventory selectOne(Long id) {
        return this.inventoryService.queryById(id);
    }

}