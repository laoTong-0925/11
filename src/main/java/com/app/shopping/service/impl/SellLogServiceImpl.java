package com.app.shopping.service.impl;

import com.app.shopping.mapper.SellLogMapper;
import com.app.shopping.model.entity.SellLog;
import com.app.shopping.service.SellLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 销量表(SellLog)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 18:29:41
 */
@Service("sellLogService")
public class SellLogServiceImpl implements SellLogService {
    @Autowired
    private SellLogMapper sellLogMapper;

    @Override
    public List<SellLog> selectByPage(int page) {
        return null;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SellLog queryById(Long id) {
        return this.sellLogMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SellLog> queryAllByLimit(int offset, int limit) {
        return this.sellLogMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sellLog 实例对象
     * @return 实例对象
     */
    @Override
    public SellLog insert(SellLog sellLog) {
        this.sellLogMapper.insert(sellLog);
        return sellLog;
    }

    /**
     * 修改数据
     *
     * @param sellLog 实例对象
     * @return 实例对象
     */
    @Override
    public SellLog update(SellLog sellLog) {
        this.sellLogMapper.update(sellLog);
        return this.queryById(sellLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sellLogMapper.deleteById(id) > 0;
    }
}