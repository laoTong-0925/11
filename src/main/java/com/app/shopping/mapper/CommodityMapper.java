package com.app.shopping.mapper;

import com.app.shopping.model.entity.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 商品表(Commodity)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-21 18:29:13
 */
public interface CommodityMapper {

    List<Commodity> getACommodityByEndTimeAndIsT(Date endTime);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Commodity queryById(Long id);

    int updateImg(Long id, String img);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Commodity> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param commodity 实例对象
     * @return 对象列表
     */
    List<Commodity> queryAll(Commodity commodity);
    List<Commodity> queryByUserId(long userId);
    int queryCountByUserId(long userId);

    List<Commodity> queryByName(@Param("name") String name,int offset,int limit);

    int queryCount();



    /**
     * 新增数据
     *
     * @param commodity 实例对象
     * @return 影响行数
     */
    int insert(Commodity commodity);

    /**
     * 修改数据
     *
     * @param commodity 实例对象
     * @return 影响行数
     */
    int update(Commodity commodity);
    int updateIsTById(long id,int isT);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
    int removeById(Long id);

}