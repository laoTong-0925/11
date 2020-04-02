package com.app.shopping.service.impl;

import com.app.shopping.mapper.UserMapper;
import com.app.shopping.model.User;
import com.app.shopping.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean userIsExistByNkName(String nkName) {
        User user = userMapper.selectUserByNkName(nkName);
        if (Objects.isNull(user)) {
            log.info("userIsExistByNkName() 查询出的user为null");
            return false;
        }
        log.info("userIsExistByNkName() 查询出的user为: {}", user);
        return true;
    }

    @Override
    public boolean userIsExistByPhone(String phone) {
        User user = userMapper.selectUserByPhone(phone);
        if (Objects.isNull(user)) {
            log.info("userIsExistByPhone() 查询出的user为null");
            return false;
        }
        log.info("userIsExistByPhone() 查询出的user为: {}", user);
        return true;
    }

    @Override
    public User selectByPhone(String phone) {
        User user = userMapper.selectUserByPhone(phone);
        if (Objects.isNull(user)) {
            log.info("selectByPhone() 查询出的user为null");
        } else {
            log.info("selectByPhone() 查询出的user为: {}", user);
        }
        return user;
    }


}
