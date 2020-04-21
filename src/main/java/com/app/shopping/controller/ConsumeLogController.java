package com.app.shopping.controller;

import com.app.shopping.model.User;
import com.app.shopping.model.entity.ConsumeLog;
import com.app.shopping.model.entity.UserInfo;
import com.app.shopping.model.vo.PageVo;
import com.app.shopping.service.ConsumeLogService;
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
@RequestMapping("/consumeLog")
public class ConsumeLogController {
    /**
     * 服务对象
     */
    @Autowired
    private ConsumeLogService consumeLogService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/selectOne")
    public ConsumeLog selectOne(Long id) {
        return this.consumeLogService.queryById(id);
    }

    /**
     * 分页查寻
     *
     * @param nowPage
     * @return
     */
    @RequestMapping("/select-page")
    @ResponseBody
    public PageVo<List<ConsumeLog>> selectByPage(@RequestParam(value = "nowPage", defaultValue = "1", required = false) int nowPage,
                                                 @RequestParam(value = "nkName") String nkName) {
        User user = userService.selectByNkname(nkName);
        List<ConsumeLog> consumeLogs = consumeLogService.selectByPage(user.getId(), nowPage - 1, 10);
        int sum = consumeLogService.selectSum(user.getId());
        return new PageVo<>(sum, nowPage, consumeLogs);
    }

    @RequestMapping("/consumeLog")
    public ModelAndView myBalance(@RequestParam(value = "nkName") String nkName) {
        ModelAndView mv = new ModelAndView();
        User user = userService.selectByNkname(nkName);
        UserInfo userInfo = userInfoService.queryByUserId(user.getId());

        mv.addObject("balance", userInfo.getBalance());
        mv.setViewName("balance");
        return mv;
    }



}