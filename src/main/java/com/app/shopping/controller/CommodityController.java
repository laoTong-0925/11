package com.app.shopping.controller;

import com.app.shopping.mapper.CommodityMapper;
import com.app.shopping.mapper.InventoryMapper;
import com.app.shopping.model.vo.CommodityVo;
import com.app.shopping.model.vo.ManageCommoditVo;
import com.app.shopping.model.Release;
import com.app.shopping.model.User;
import com.app.shopping.model.entity.Commodity;
import com.app.shopping.model.entity.Inventory;
import com.app.shopping.model.entity.UserConsignee;
import com.app.shopping.service.*;
import com.app.shopping.util.DateUtil;
import com.app.shopping.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * 商品表(Commodity)表控制层
 *
 * @author makejava
 * @since 2020-04-21 18:29:18
 */
@Controller
public class CommodityController {
    @Autowired
    private UserImgService imgService;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private UserService userService;
    @Autowired
    private MyConsigneeService consigneeService;
    /**
     * 商品展示
     *
     * @param id 主键
     * @return 单条数据
     */
    /**
     * 商品目录/搜索商品
     *
     * @return
     */
    @RequestMapping("/commodityList")
    public ModelAndView commodityList(@RequestParam(value = "index", defaultValue = "0", required = false) int index,
                                      @RequestParam(value = "spName", required = false) String spName,
                                      @RequestParam(value = "nkName", required = false) String nkName) {

        ModelAndView mv = new ModelAndView();
        if (index < 0)
            index = 0;
        List<Commodity> commodities = new ArrayList<>();
        if (StringUtils.isBlank(spName)) {//无搜索条件
            commodities = commodityMapper.queryAllByLimit(index * 10, (index + 1) * 10);
        } else {
            commodities = commodityMapper.queryByName(spName, index * 10, (index + 1) * 10);
        }
        List<CommodityVo> vos = new ArrayList<>();
        if (commodities.size() != 0)
            for (Commodity e : commodities) {
                List<Inventory> inventories = inventoryMapper.queryByCId(e.getId());
                inventories.forEach(v -> {
                    CommodityVo vo = new CommodityVo(e.getId(), v.getProperty(), v.getInventory(),
                            e.getName(), e.getIsTicket(), v.getMoney(), e.getImg());
                    vos.add(vo);
                });
            }
        if (StringUtils.isNotBlank(nkName)) {//登录状态
            mv.addObject("nkName", nkName);
        }

        int lastPage = commodities.size() / 10;
        mv.addObject("commodityList", vos);
        mv.addObject("count", vos.size());
        mv.addObject("lastPage", lastPage);
        mv.addObject("index", index);

        mv.setViewName("commodityList");

        return mv;
    }

    /**
     * 单个商品
     *
     * @param id
     * @param nkName
     * @return
     */
    @RequestMapping("/commodity")
    public ModelAndView selectOne(@RequestParam(value = "id") Long id,
                                  @RequestParam(value = "nkName", required = false) String nkName) {
        Commodity commodity = commodityMapper.queryById(id);
        ModelAndView mv = new ModelAndView();

        List<Inventory> inventories = inventoryMapper.queryByCId(id);
        //库存
        int sumInventor = inventories.stream().mapToInt(Inventory::getInventory).sum();
        //todo销量
        //收货地址
        if (StringUtils.isNotBlank(nkName)) {
            User user = userService.selectByNkname(nkName);
            List<UserConsignee> consignees = consigneeService.queryByUserId(user.getId());
            mv.addObject("consignees", consignees);

        }
        mv.addObject("isTicket", commodity.getIsTicket());
        mv.addObject("sumInventor", sumInventor);
//        mv.addObject("inventory", inventory);
        mv.addObject("commodity", commodity);
        mv.addObject("sumInventor", sumInventor);
        mv.addObject("proMap", inventories);

        mv.setViewName("commodity");
        return mv;
    }

//    /**
//     * 搜索商品
//     *
//     * @param name
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("search")
//    public Result commoditySearch(String name) {
//
//
//    }

    /**
     * 商品发布
     *
     * @return
     */
    @RequestMapping("/commodityRelease")
    public ModelAndView admin() {

        ModelAndView mv = new ModelAndView("commodityRelease");
        return mv;
    }

    /**
     * 商品管理页面
     *
     * @return
     */
    @RequestMapping("/commodityManage")
    public ModelAndView commodityAdminList(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                           @RequestParam(value = "index", defaultValue = "0") Integer index,//-1
                                           @RequestParam(value = "limit", defaultValue = "0") Integer limit) {
        //所有商品
        if (index < 0)
            index = 0;
        int count = commodityMapper.queryCount();
        List<ManageCommoditVo> manageCommoditVos = commodityService.manageCommodityV(index * 10, (index + 1) * 10);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("commodityManage");
        mv.addObject("manageCommoditVos", manageCommoditVos);
        mv.addObject("count", count);
        mv.addObject("index", index);
        mv.addObject("lastPage", count / 10);

        return mv;
    }

    /**
     * 商品图片上传
     *
     * @param file
     * @param id
     * @return
     */
    @RequestMapping("/admin-loadImg")
    public ModelAndView load(@RequestParam("file") MultipartFile file, @RequestParam("cId") Integer id) {
        boolean b = imgService.loadImg(file, id);
        return new ModelAndView("redirect:commodityManage");
    }

    /**
     * 管理员修改商品
     *
     * @param cId
     * @param pro1
     * @param pro1Kc
     * @param pro1m
     * @param pro2
     * @param pro2Kc
     * @param pro2m
     * @param pro3
     * @param pro3Kc
     * @param pro3m
     * @return
     */
    @RequestMapping("/admin-sp-modify")
    @ResponseBody
    @Transactional
    public Result modify(long cId,
                         @RequestParam(value = "pro1", required = false) String pro1,
                         @RequestParam(value = "pro1Kc", required = false, defaultValue = "0") Integer pro1Kc,
                         @RequestParam(value = "pro1m", required = false) String pro1m,
                         @RequestParam(value = "pro2", required = false) String pro2,
                         @RequestParam(value = "pro2Kc", required = false, defaultValue = "0") Integer pro2Kc,
                         @RequestParam(value = "pro2m", required = false) String pro2m,
                         @RequestParam(value = "pro3", required = false) String pro3,
                         @RequestParam(value = "pro3Kc", required = false, defaultValue = "0") Integer pro3Kc,
                         @RequestParam(value = "pro3m", required = false) String pro3m,
                         @RequestParam(value = "isT", required = false) String isT
    ) {
        if (StringUtils.isBlank(pro1))
            return Result.failed();
        List<Inventory> inventories = new ArrayList<>();
        inventories.add(new Inventory(cId, pro1, pro1Kc, pro1m));
        if (StringUtils.isNotBlank(pro2) && StringUtils.isNotBlank(pro2m) && pro2Kc != 0)
            inventories.add(new Inventory(cId, pro2, pro2Kc, pro2m));

        if (StringUtils.isNotBlank(pro3) && StringUtils.isNotBlank(pro3m) && pro3Kc != 0)
            inventories.add(new Inventory(cId, pro3, pro3Kc, pro3m));
        //修改是否抽奖
        if (StringUtils.isNotBlank(isT)) {
            int isTicket = "否".equals(isT) ? 0 : 1;
            if (1 == isTicket) {
                //设置活动过期时间
                Commodity commodity = new Commodity();
                commodity.setId(cId);
                commodity.setAEndTime(DateUtil.setActivityExTIme());
                int update = commodityMapper.update(commodity);
            }
            int i = inventoryMapper.updateIsTByCId(cId, isTicket);
            int i1 = commodityMapper.updateIsTById(cId, isTicket);
        }

        //修改信息
        inventories.forEach(e -> {
            int i = inventoryMapper.updateInventoryAndMoneyByCIdAndPro(e.getInventory(), e.getMoney(), cId, e.getProperty());
        });
        return Result.success();
    }

    @RequestMapping("/admin/remove/sp")
    @ResponseBody
    @Transactional
    public Result adminRemoveSp(Long cId) {
        if (null == cId)
            return Result.failed();
        int i = commodityMapper.removeById(cId);
        int i1 = inventoryMapper.removeByCId(cId);
        if (i != 0 && i1 != 0)
            return Result.success();
        return Result.failed();
    }


    /**
     * 发布商品
     *
     * @param name
     * @param detail
     * @param pro1
     * @param pro1Kc
     * @param pro1m
     * @param pro2
     * @param pro2Kc
     * @param pro2m
     * @param pro3
     * @param pro3Kc
     * @param pro3m
     * @return
     */
    @RequestMapping("/admin/release")
    @ResponseBody
    public Result admin(@RequestParam(value = "name") String name,
                        @RequestParam(value = "detail", required = false) String detail,
                        @RequestParam(value = "pro1", required = false) String pro1,
                        @RequestParam(value = "pro1Kc", required = false, defaultValue = "0") Integer pro1Kc,
                        @RequestParam(value = "pro1m", required = false) String pro1m,
                        @RequestParam(value = "pro2", required = false) String pro2,
                        @RequestParam(value = "pro2Kc", required = false, defaultValue = "0") Integer pro2Kc,
                        @RequestParam(value = "pro2m", required = false) String pro2m,
                        @RequestParam(value = "pro3", required = false) String pro3,
                        @RequestParam(value = "pro3Kc", required = false, defaultValue = "0") Integer pro3Kc,
                        @RequestParam(value = "pro3m", required = false) String pro3m
    ) {
        List<Release> list = new ArrayList<>();
        list.add(new Release(pro1, pro1m, pro1Kc));
        if (StringUtils.isNotBlank(pro2) && StringUtils.isNotBlank(pro2m) && pro2Kc != 0)
            list.add(new Release(pro2, pro2m, pro2Kc));
        if (StringUtils.isNotBlank(pro3) && StringUtils.isNotBlank(pro3m) && pro3Kc != 0)
            list.add(new Release(pro3, pro3m, pro3Kc));

        Result release = activityService.release(name, detail, list);
        return release;
    }

}