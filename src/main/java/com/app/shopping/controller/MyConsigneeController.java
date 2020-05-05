package com.app.shopping.controller;

import com.app.shopping.model.User;
import com.app.shopping.model.entity.UserConsignee;
import com.app.shopping.model.entity.UserImg;
import com.app.shopping.service.MyConsigneeService;
import com.app.shopping.service.UserImgService;
import com.app.shopping.service.UserService;
import com.app.shopping.util.Result;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
public class MyConsigneeController {

    @Autowired
    UserService userService;
    @Autowired
    UserImgService userImgService;
    @Autowired
    MyConsigneeService consigneeService;

    /**
     * 收货地址
     *
     * @return
     */
    @RequestMapping("/myConsignee")
    public ModelAndView userConsignee(@RequestParam("nkName") String nkName) {
        ModelAndView mv = new ModelAndView();
        if (Strings.isBlank(nkName)) {
            mv.setViewName("login");
        }
        User user = userService.selectByNkname(nkName);
        UserImg userImg = userImgService.queryById(user.getId());
        List<UserConsignee> userConsignees = consigneeService.queryByUserId(user.getId());
        mv.addObject("user", user);
        mv.addObject("userConsignees", userConsignees);
        mv.addObject("userImg", userImg.getUserImg());
        mv.setViewName("myConsignee");
        return mv;
    }

    @RequestMapping("/myConsignee/update")
    @ResponseBody
    public Result userConsigneeUpdate(@RequestParam("nkName") String nkName,
                                            @RequestParam("consigneeId") long consigneeId,
                                            @RequestParam(value = "consignee", required = false, defaultValue = "") String consignee,
                                            @RequestParam(value = "consigneeMan", required = false, defaultValue = "") String consigneeMan,
                                            @RequestParam(value = "phone", required = false, defaultValue = "") String phone) {
        ModelAndView mv = new ModelAndView();
        if (Strings.isBlank(nkName)) {
            mv.setViewName("login");
        }
        User user = userService.selectByNkname(nkName);
        boolean b = consigneeService.updateAllById(consigneeId,consignee,consigneeMan,phone);
        if (b)
            return Result.success();
        return Result.failed();
    }

    @RequestMapping("/myConsignee/del")
    @ResponseBody
    public Result userConsigneeDel(@RequestParam("nkName") String nkName,
                                   @RequestParam("consigneeId") long consigneeId) {
        if (null == nkName || consigneeId == 0)
            return Result.failed();
        User user = userService.selectByNkname(nkName);
        List<UserConsignee> userConsignees = consigneeService.queryByUserId(user.getId());
        List<Long> collect = userConsignees.stream().map(UserConsignee::getId).collect(Collectors.toList());
        if (!collect.contains(consigneeId))//判断存在
            return Result.failed();
        boolean b = consigneeService.deleteById((int) consigneeId);
        if (b)
            return Result.success();
        return Result.failed();
    }

    @RequestMapping("/myConsignee/add")
    @ResponseBody
    public Result userConsigneeAdd(@RequestParam("nkName") String nkName,
                                         @RequestParam(value = "consignee", defaultValue = "") String consignee,
                                         @RequestParam(value = "consigneeMan", defaultValue = "") String consigneeMan,
                                         @RequestParam(value = "phone", defaultValue = "") String phone) {
        if (Strings.isBlank(nkName)) {
            return Result.failed();
        }
        User user = userService.selectByNkname(nkName);
        List<UserConsignee> userConsignees1 = consigneeService.queryByUserId(user.getId());

        UserImg userImg = userImgService.queryById(user.getId());
        if (userConsignees1.size() >= 3)
            return Result.failed("收货地址数量只能有3个");
        UserConsignee insert = consigneeService.insert(new UserConsignee(user.getId(), consignee, consigneeMan, phone));
        return Result.success();
    }


}
