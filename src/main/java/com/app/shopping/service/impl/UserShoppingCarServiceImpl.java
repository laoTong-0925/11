package com.app.shopping.service.impl;

import com.app.shopping.mapper.UserShoppingCarMapper;
import com.app.shopping.model.entity.UserShoppingCar;
import com.app.shopping.service.UserShoppingCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车(UserShoppingCar)表服务实现类
 *
 * @author makejava
 * @since 2020-04-22 12:40:41
 */
@Service("userShoppingCarService")
public class UserShoppingCarServiceImpl implements UserShoppingCarService {
    @Resource
    private UserShoppingCarMapper userShoppingCarDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserShoppingCar queryById(Long id) {
        return this.userShoppingCarDao.queryById(id);
    }

    @Override
    public List<UserShoppingCar> queryByUserId(Long userId) {

        return userShoppingCarDao.queryByUserId(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserShoppingCar> queryAllByLimit(int offset, int limit) {
        return this.userShoppingCarDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userShoppingCar 实例对象
     * @return 实例对象
     */
    @Override
    public UserShoppingCar insert(UserShoppingCar userShoppingCar) {
        this.userShoppingCarDao.insert(userShoppingCar);
        return userShoppingCar;
    }

    /**
     * 修改数据
     *
     * @param userShoppingCar 实例对象
     * @return 实例对象
     */
    @Override
    public UserShoppingCar update(UserShoppingCar userShoppingCar) {
        this.userShoppingCarDao.update(userShoppingCar);
        return this.queryById(userShoppingCar.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userShoppingCarDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByUserIdAndCommodity(Long userId, Long commodityId, String commodityPro) {
        boolean r = userShoppingCarDao.deleteByUserIdAndCommodity(userId, commodityId, commodityPro) > 0;
        return r;
    }
}