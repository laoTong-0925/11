package com.app.shopping.service;

import com.app.shopping.model.entity.ConsumeLog;
import java.util.List;

/**
 * 消费记录(ConsumeLog)表服务接口
 *
 * @author makejava
 * @since 2020-04-21 18:29:20
 */
public interface ConsumeLogService {

    List<ConsumeLog> selectByPage(long userId, int page,int pageSize);

    int selectSum(long userId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ConsumeLog queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ConsumeLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param consumeLog 实例对象
     * @return 实例对象
     */
    ConsumeLog insert(ConsumeLog consumeLog);

    /**
     * 修改数据
     *
     * @param consumeLog 实例对象
     * @return 实例对象
     */
    ConsumeLog update(ConsumeLog consumeLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}