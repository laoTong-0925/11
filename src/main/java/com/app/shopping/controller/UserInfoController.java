package com.app.shopping.controller;

import com.app.shopping.model.User;
import com.app.shopping.service.UserInfoService;
import com.app.shopping.service.UserService;
import com.app.shopping.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户信息，余额(UserInfo)表控制层
 *
 * @author makejava
 * @since 2020-04-21 20:27:32
 */
@Controller
public class UserInfoController {
    /**
     * 服务对象
     */
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @RequestMapping("/userInfo-update")
    @ResponseBody
    public Result userInfoUpdate(@RequestParam(value = "nkName") String nkName,
                                 @RequestParam(value = "phone", required = false) String phone,
                                 @RequestParam(value = "eMail", required = false) String eMail) {
        User user = userService.selectByNkname(nkName);
        if (null != user) {
            if (StringUtils.isNotBlank(phone))
                user.setPhone(phone);
            if (StringUtils.isNotBlank(eMail))
                user.setEMail(eMail);
        } else {
            return Result.failed();
        }
        int update = userService.update(user);
        if (update != 0)
            return Result.success();
        return Result.failed();


    }


}