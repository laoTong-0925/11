package com.app.shopping.service.impl;

import com.app.shopping.model.entity.ConsumeLog;
import com.app.shopping.mapper.ConsumeLogMapper;
import com.app.shopping.service.ConsumeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

/**
 * 消费记录(ConsumeLog)表服务实现类
 *
 * @author makejava
 * @since 2020-04-21 18:29:25
 */
@Service("consumeLogService")
public class ConsumeLogServiceImpl implements ConsumeLogService {
    @Autowired
    private ConsumeLogMapper consumeLogMapper;

    @Override
    public List<ConsumeLog> selectByPage(long userId, int page, int pageSize) {
        List<ConsumeLog> consumeLogs = consumeLogMapper.selectByPage(userId, page, pageSize);
        if (null == consumeLogs || consumeLogs.isEmpty())
            return null;
        return consumeLogs;
    }

    @Override
    public int selectSum(long userId) {
        return  consumeLogMapper.selectSum(userId);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ConsumeLog queryById(Long id) {
        return this.consumeLogMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ConsumeLog> queryAllByLimit(int offset, int limit) {
        return this.consumeLogMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param consumeLog 实例对象
     * @return 实例对象
     */
    @Override
    public ConsumeLog insert(ConsumeLog consumeLog) {
        this.consumeLogMapper.insert(consumeLog);
        return consumeLog;
    }

    /**
     * 修改数据
     *
     * @param consumeLog 实例对象
     * @return 实例对象
     */
    @Override
    public ConsumeLog update(ConsumeLog consumeLog) {
        this.consumeLogMapper.update(consumeLog);
        return this.queryById(consumeLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.consumeLogMapper.deleteById(id) > 0;
    }
}