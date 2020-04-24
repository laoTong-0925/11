package com.app.shopping.controller;

import com.app.shopping.model.User;
import com.app.shopping.model.entity.ConsumeLog;
import com.app.shopping.model.entity.UserImg;
import com.app.shopping.service.ConsumeLogService;
import com.app.shopping.service.UserImgService;
import com.app.shopping.service.UserInfoService;
import com.app.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 消费记录(ConsumeLog)表控制层
 *
 * @author makejava
 * @since 2020-04-21 18:29:26
 */
@Controller
public class ConsumeLogController {
    /**
     * 服务对象
     */
    @Autowired
    private ConsumeLogService consumeLogService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserImgService userImgService;




    /**
     * 分页查寻,消费记录
     *
     * @param index
     * @param nkName
     * @return
     */
    @RequestMapping("/consumeLog")
    @ResponseBody
    public ModelAndView selectByPage(@RequestParam(value = "index", defaultValue = "0", required = false) int index,
                                     @RequestParam(value = "nkName") String nkName) {
        ModelAndView mv = new ModelAndView();
        User user = userService.selectByNkname(nkName);
        if (null == user) {
            mv.setViewName("login");
            return mv;
        }
        if (index < 0)
            index = 0;
        UserImg userImg = userImgService.queryById(user.getId());
        List<ConsumeLog> consumeLogs = consumeLogService.selectByPage(user.getId(), index, 10);
        int size = consumeLogService.selectSum(user.getId());
        mv.addObject("consumeLogList", consumeLogs);
        mv.addObject("userName", user.getNkName());
        mv.addObject("userImg",userImg.getUserImg());
        mv.addObject("pageSize", 10);
        mv.addObject("size", size);
        mv.addObject("lastPage", size/10);
        mv.addObject("index", index);
        mv.setViewName("consumeLog");
        return mv;
    }


}