package com.app.shopping.controller;

import com.app.shopping.model.entity.Commodity;
import com.app.shopping.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 商品表(Commodity)表控制层
 *
 * @author makejava
 * @since 2020-04-21 18:29:18
 */
@RestController
@RequestMapping("commodity")
public class CommodityController {
    /**
     * 服务对象
     */
    @Autowired
    private CommodityService commodityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectOne")
    public Commodity selectOne(Long id) {
        return this.commodityService.queryById(id);
    }

}