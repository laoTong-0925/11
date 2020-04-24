package com.app.shopping.service.impl;

import com.app.shopping.mapper.InventoryMapper;
import com.app.shopping.model.ManageCommoditVo;
import com.app.shopping.model.entity.Commodity;
import com.app.shopping.mapper.CommodityMapper;
import com.app.shopping.model.entity.Inventory;
import com.app.shopping.model.vo.CommodityVo;
import com.app.shopping.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品表(Commodity)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 18:29:16
 */
@Service()
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private InventoryMapper inventoryMapper;


    @Override
    public List<ManageCommoditVo> manageCommodityV(int offset, int limit) {
        //所有商品 10
        List<Commodity> commodities = commodityMapper.queryAllByLimit(offset, limit);
//        List<Long> commodityIds = commodities.stream().map(Commodity::getId).collect(Collectors.toList());
        List<ManageCommoditVo> manageCommoditVos = new ArrayList<>();
        commodities.forEach(e -> {
            //商品id 的库存
            List<Inventory> inventories = inventoryMapper.queryByCId(e.getId());
            List<Integer> inventoryKc = inventories.stream().map(Inventory::getInventory).collect(Collectors.toList());
            List<String> pros = inventories.stream().map(Inventory::getProperty).collect(Collectors.toList());
            List<String> moneys = inventories.stream().map(Inventory::getMoney).collect(Collectors.toList());
            String ticket = e.getIsTicket() == 1 ? "是" : "否";
            manageCommoditVos.add(new ManageCommoditVo(e.getName(), e.getDetail(), e.getImg(),
                    pros, moneys, inventoryKc, ticket, e.getId()));
        });

        return manageCommoditVos;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Commodity queryById(Long id) {
        return this.commodityMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Commodity> queryAllByLimit(int offset, int limit) {
        return this.commodityMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param commodity 实例对象
     * @return 实例对象
     */
    @Override
    public Commodity insert(Commodity commodity) {
        this.commodityMapper.insert(commodity);
        return commodity;
    }

    /**
     * 修改数据
     *
     * @param commodity 实例对象
     * @return 实例对象
     */
    @Override
    public Commodity update(Commodity commodity) {
        this.commodityMapper.update(commodity);
        return this.queryById(commodity.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.commodityMapper.deleteById(id) > 0;
    }
}