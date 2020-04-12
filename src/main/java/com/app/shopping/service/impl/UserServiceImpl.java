package com.app.shopping.service.impl;

import com.app.shopping.cache.JedisUtil;
import com.app.shopping.mapper.UserMapper;
import com.app.shopping.model.User;
import com.app.shopping.service.UserService;
import com.app.shopping.service.message.SMSService;
import com.app.shopping.util.CodeUtil;
import com.app.shopping.util.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SMSService smsService;
    @Autowired
    JedisUtil jedisUtil;

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

    @Override
    public User selectByNkname(String nkName) {
        User user = userMapper.selectUserByNkName(nkName);
        if (Objects.isNull(user)) {
            log.info("userIsExistByNkName() 查询出的user为null");
            return null;
        }
        log.info("userIsExistByNkName() 查询出的user为: {}", user);
        return user;
    }



    @Override
    public Result register(String nkName, String phone, String passWord, String vCode) {
        String s = CodeUtil.MD5toDo("11111q");
        log.info(s);
        /*
        查验证码过期
         */
        boolean bV = smsService.vCodeIsExpire(phone, vCode);
        if (!bV)//验证码不存在或不相等
            return Result.validateFailed();
        boolean b = userIsExistByPhone(phone);
        boolean b1 = userIsExistByNkName(nkName);
        if (b && b1) {
            return Result.validateFailed("昵称或手机已被注册");
        }
        User user = new User();
        user.setNkName(nkName);
        user.setPhone(phone);
        user.setPassWord(passWord);
        int insert = userMapper.insert(user);
        if (insert == 1) {
            return Result.success("");
        } else {
            return Result.failed();
        }
    }

    @Override
    public Result perfectInfo(String nkName, String name, String eMail) {
        int i = userMapper.setNameAndEmailByNkName(nkName, name, eMail);
        log.info("注册完成消息名字，邮箱结果：{}", i);
        return Result.success("");
    }

    @Override
    public boolean verifyAccount(String nkName, String phone, String passWord) {
        User user = userMapper.verifyAccount(nkName, phone, passWord);
        if (Objects.isNull(user)) {
            log.info("用户不存在 nkName:{} phone:{} password:{}", nkName, phone, passWord);
            return false;
        }
        return true;
    }

}
