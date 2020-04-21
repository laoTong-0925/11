package com.app.shopping.mapper;

import com.app.shopping.model.entity.ConsumeLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消费记录(ConsumeLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-21 18:29:19
 */
public interface ConsumeLogMapper {

    int selectSum(long userId);

    List<ConsumeLog> selectByPage(long userId, int offset, int limit);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ConsumeLog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ConsumeLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param consumeLog 实例对象
     * @return 对象列表
     */
    List<ConsumeLog> queryAll(ConsumeLog consumeLog);

    /**
     * 新增数据
     *
     * @param consumeLog 实例对象
     * @return 影响行数
     */
    int insert(ConsumeLog consumeLog);

    /**
     * 修改数据
     *
     * @param consumeLog 实例对象
     * @return 影响行数
     */
    int update(ConsumeLog consumeLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}