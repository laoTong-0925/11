package com.app.shopping.service.impl;

import com.app.shopping.service.LoginAndRegisterService;
import com.app.shopping.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class LoginAndRegisterServiceImpl implements LoginAndRegisterService {

    @Autowired
    UserService userService;

    /**
     * 验证用户的有效性
     *
     * @param account
     * @param passWord
     * @return
     */
    @Override
    public boolean verifyAccount(String account, String passWord) {
        boolean accountIsPhone = false;//account是手机号为true
        try {
            int i = Integer.parseInt(account);
            accountIsPhone = true;
            log.info("：{} 用户使用phone登录", account);
        } catch (NumberFormatException e) {
            log.info("：{} 用户使用nkName登录", account);
        }

        if (accountIsPhone) {//phone 手机登录
            if (userService.userIsExistByPhone(account)) {
                return userService.verifyAccount("", account, passWord);
            }
        } else {//nkName 昵称登录
            if (userService.userIsExistByNkName(account)) {
                //昵称登录
                return userService.verifyAccount(account, "", passWord);
            }
        }
        return false;
    }

}
