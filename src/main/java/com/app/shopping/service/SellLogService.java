package com.app.shopping.service;

import com.app.shopping.model.entity.SellLog;

import java.util.List;

/**
 * 销量表(SellLog)表服务接口
 *
 * @author makejava
 * @since 2020-04-21 18:29:40
 */
public interface SellLogService {

    /**
     * 分页查询
     *
     * @param page 当前页
     * @return
     */
    List<SellLog> selectByPage(int page);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SellLog queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SellLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sellLog 实例对象
     * @return 实例对象
     */
    SellLog insert(SellLog sellLog);

    /**
     * 修改数据
     *
     * @param sellLog 实例对象
     * @return 实例对象
     */
    SellLog update(SellLog sellLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}