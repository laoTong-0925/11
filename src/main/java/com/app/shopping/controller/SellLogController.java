package com.app.shopping.controller;

import com.app.shopping.model.entity.SellLog;
import com.app.shopping.model.vo.PageVo;
import com.app.shopping.service.SellLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 销量表(SellLog)表控制层
 *
 * @author makejava
 * @since 2020-04-21 18:29:41
 */
@Controller
@RequestMapping("/sellLog")
public class SellLogController {
    /**
     * 服务对象
     */
    @Autowired
    private SellLogService sellLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("/selectOne")
    public SellLog selectOne(Long id) {
        return this.sellLogService.queryById(id);
    }

//    @RequestMapping("/select-page")
//    public PageVo<List<SellLog>> selectByPage(@RequestParam(value = "nowPage", defaultValue = "1",required = false) int nowPage) {
//
//        List<SellLog> sellLogs = sellLogService.selectByPage(nowPage - 1);
//    }


}