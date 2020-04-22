package com.app.shopping.mapper;


import com.app.shopping.model.entity.UserConsignee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserConsignee)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-12 17:32:26
 */
@Mapper
public interface MyConsigneeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserConsignee queryById(Long id);
    List<UserConsignee> queryByUserId(Long userId);

    int updateById(Long id);
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserConsignee> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userConsignee 实例对象
     * @return 对象列表
     */
    List<UserConsignee> queryAll(UserConsignee userConsignee);

    /**
     * 新增数据
     *
     * @param userConsignee 实例对象
     * @return 影响行数
     */
    int insert(UserConsignee userConsignee);

    /**
     * 修改数据
     *
     * @param userConsignee 实例对象
     * @return 影响行数
     */
    int update(UserConsignee userConsignee);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}