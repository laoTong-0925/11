package com.app.shopping.service.impl;


import com.app.shopping.mapper.UserImgMapper;
import com.app.shopping.model.entity.UserImg;
import com.app.shopping.service.UserImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserImg)表服务实现类
 *
 * @author makejava
 * @since 2020-04-12 17:37:06
 */
@Service("userImgService")
public class UserImgServiceImpl implements UserImgService {
    @Resource
    private UserImgMapper userImgMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserImg queryById(Long id) {
        return this.userImgMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserImg> queryAllByLimit(int offset, int limit) {
        return this.userImgMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userImg 实例对象
     * @return 实例对象
     */
    @Override
    public UserImg insert(UserImg userImg) {
        this.userImgMapper.insert(userImg);
        return userImg;
    }

    /**
     * 修改数据
     *
     * @param userImg 实例对象
     * @return 实例对象
     */
    @Override
    public UserImg update(UserImg userImg) {
        this.userImgMapper.update(userImg);
        return this.queryById(userImg.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userImgMapper.deleteById(id) > 0;
    }
}