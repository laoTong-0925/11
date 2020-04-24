package com.app.shopping.mapper;


import com.app.shopping.model.entity.UserImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (UserImg)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-12 17:37:05
 */
@Mapper
public interface UserImgMapper {


    int updateImgByUserId(String img,long userId);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserImg queryById(Long userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserImg> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userImg 实例对象
     * @return 对象列表
     */
    List<UserImg> queryAll(UserImg userImg);

    /**
     * 新增数据
     *
     * @param userImg 实例对象
     * @return 影响行数
     */
    int insert(UserImg userImg);

    /**
     * 修改数据
     *
     * @param userImg 实例对象
     * @return 影响行数
     */
    int update(UserImg userImg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}