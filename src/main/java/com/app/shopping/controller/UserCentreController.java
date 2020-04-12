package com.app.shopping.controller;

import com.app.shopping.model.User;
import com.app.shopping.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j2
public class UserCentreController {

    @Autowired
    UserService userService;

    /**
     * 用户信息
     *
     * @return
     */
    @RequestMapping("/userInfo")
    public ModelAndView userInfo(@RequestParam("nkName") String nkName) {
        ModelAndView mv = new ModelAndView();
        if (Strings.isBlank(nkName)){
            mv.setViewName("login");
        }
        User user = userService.selectByNkname(nkName);
        mv.addObject("user",user);
        return mv;
    }

    /**
     * 用户中心
     *
     * @return
     */
    @RequestMapping("/userCentre")
    public ModelAndView userCentre(@RequestParam("nkName") String nkName) {
        ModelAndView mv = new ModelAndView();
        if (Strings.isBlank(nkName)){
            mv.setViewName("login");
        }
        mv.setViewName("userCentre");
        return mv;
    }


}
