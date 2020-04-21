package com.app.shopping.service.impl;

import com.app.shopping.model.entity.Commodity;
import com.app.shopping.mapper.CommodityMapper;
import com.app.shopping.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 商品表(Commodity)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 18:29:16
 */
@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;

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
     * @param limit 查询条数
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