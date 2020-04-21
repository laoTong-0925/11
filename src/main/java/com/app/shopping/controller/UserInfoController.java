package com.app.shopping.controller;

import com.app.shopping.model.User;
import com.app.shopping.model.entity.UserInfo;
import com.app.shopping.service.UserInfoService;
import com.app.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



/**
 * 用户信息，余额(UserInfo)表控制层
 *
 * @author makejava
 * @since 2020-04-21 20:27:32
 */
@RestController
@RequestMapping("/userInfo")
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
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/selectOne")
    public UserInfo selectOne(Integer id) {
        return this.userInfoService.queryById(Long.valueOf(id));
    }

    @RequestMapping("/balance")
    public ModelAndView myBalance(@RequestParam(value = "nkName") String nkName) {
        ModelAndView mv = new ModelAndView();
        User user = userService.selectByNkname(nkName);
        UserInfo userInfo = userInfoService.queryByUserId(user.getId());

        mv.addObject("balance", userInfo.getBalance());
        mv.setViewName("balance");
        return mv;
    }


}