package com.app.shopping.service;


import com.app.shopping.model.entity.UserImg;

import java.util.List;

/**
 * (UserImg)表服务接口
 *
 * @author makejava
 * @since 2020-04-12 17:37:06
 */
public interface UserImgService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserImg queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserImg> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userImg 实例对象
     * @return 实例对象
     */
    int insert(UserImg userImg);

    String buPath(String fileName);
    /**
     * 修改数据
     *
     * @param userImg 实例对象
     * @return 实例对象
     */
    UserImg update(UserImg userImg);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}