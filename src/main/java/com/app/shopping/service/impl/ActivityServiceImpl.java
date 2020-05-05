package com.app.shopping.service.impl;


import com.app.shopping.mapper.ActivityMapper;
import com.app.shopping.mapper.CommodityMapper;
import com.app.shopping.mapper.InventoryMapper;
import com.app.shopping.model.Release;
import com.app.shopping.model.entity.Activity;
import com.app.shopping.model.entity.Commodity;
import com.app.shopping.model.entity.Inventory;
import com.app.shopping.service.ActivityService;
import com.app.shopping.util.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * 活动表(Activity)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 18:25:47
 */
@Service
@Log4j2
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    @Transactional
    public Result release(String name, String detail, List<Release> releaseList) {
        Commodity commodity = new Commodity(name,"123.png", detail);
        int insert = commodityMapper.insert(commodity);
        releaseList.forEach(e->{
            int i = inventoryMapper.insert(new Inventory(commodity.getId(), e.getPro(), e.getSum(), e.getMoney()));
            log.info("库存新增商品结果{}",i);
        });
        return Result.success();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Activity queryById(int id) {
        return this.activityMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Activity> queryAllByLimit(int offset, int limit) {
        return this.activityMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
    @Override
    public Activity insert(Activity activity) {
        this.activityMapper.insert(activity);
        return activity;
    }

    /**
     * 修改数据
     *
     * @param activity 实例对象
     * @return 实例对象
     */
//    @Override
//    public Activity update(Activity activity) {
//        this.activityMapper.update(activity);
//        return this.queryById(activity.getId());
//    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.activityMapper.deleteById(id) > 0;
    }
}