package com.app.shopping.controller;

import com.app.shopping.model.ModelObject;
import com.app.shopping.model.User;
import com.app.shopping.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Log4j2
@Controller
public class MyOrderController {

    @Autowired
    UserService userService;
    /**
     * 订单
     *
     * @return
     */
    @RequestMapping("/myOrder")
    public ModelAndView myOrder(@RequestParam("nkName") String nkName) {
        ModelAndView mv = new ModelAndView();
        User user = userService.selectByNkname(nkName);
        mv.addObject("user",user);
        mv.setViewName("myOrder");
        return mv;
    }
}
