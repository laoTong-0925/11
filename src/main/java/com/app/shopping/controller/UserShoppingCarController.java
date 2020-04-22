package com.app.shopping.controller;

import com.app.shopping.model.User;
import com.app.shopping.model.entity.Commodity;
import com.app.shopping.model.entity.Inventory;
import com.app.shopping.model.entity.UserImg;
import com.app.shopping.model.entity.UserShoppingCar;
import com.app.shopping.model.vo.PageVo;
import com.app.shopping.model.vo.ShoppingCarVo;
import com.app.shopping.service.*;
import com.app.shopping.util.Result;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
public class UserShoppingCarController {

    @Autowired
    UserService userService;
    @Autowired
    UserShoppingCarService shoppingCarService;
    @Autowired
    CommodityService commodityService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    UserImgService userImgService;

    /**
     * 我的(购物车)收藏
     *
     * @return
     */
    @RequestMapping("/collections")
    public ModelAndView collections(@RequestParam("nkName") String nkName) {
        ModelAndView mv = new ModelAndView();
        if (Strings.isBlank(nkName)) {
            mv.setViewName("login");
        }
        User user = userService.selectByNkname(nkName);
        List<UserShoppingCar> shoppingCarList = shoppingCarService.queryByUserId(user.getId());
        List<ShoppingCarVo> shoppingCarVoList = new ArrayList<>();
        shoppingCarList.forEach(e -> {
            Commodity commodity = commodityService.queryById(e.getCommodityId());
            Inventory inventory = inventoryService.queryByCommodityIdAndPro(e.getCommodityId(), e.getCommodityPro());
            String nowMoney = StringUtils.isEmpty(inventory.getActivityMoney()) ? inventory.getMoney() : inventory.getActivityMoney();
            Double sumMoney = Double.parseDouble(nowMoney) * Double.valueOf(e.getSum());
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String format = decimalFormat.format(sumMoney);
            new ShoppingCarVo(commodity.getImg(), commodity.getName(), inventory.getMoney(), e.getSum(), inventory.getActivityMoney(), format);
        });
        mv.addObject("shoppingCarVoList", shoppingCarVoList);
        mv.addObject("userName", user.getNkName());
        mv.setViewName("collections");
        return mv;
    }

    @RequestMapping("/collections/add-into")
    @ResponseBody
    public Result addIntoShoppingCar(@RequestParam("commodityId") long commodityId,
                                     @RequestParam("commodityPro") String commodityPro,
                                     @RequestParam("sum") int sum,
                                     @RequestParam("nkName") String nkName) {
        //查库存
        Inventory inventory = inventoryService.queryByCommodityIdAndPro(commodityId, commodityPro);
        if (inventory.getInventory() < sum) {
            return Result.failed("库存不足");//不足
        }
        Commodity commodity = commodityService.queryById(commodityId);
        User user = userService.selectByNkname(nkName);
        UserShoppingCar insert = shoppingCarService.insert(new UserShoppingCar(user.getId(), commodityId, commodityPro, sum));
        return Result.success(null, "添加购物车成功");
    }

    @RequestMapping("/collections/remove")
    public ModelAndView removeShoppingCar(@RequestParam("userCarCommodityId") long userCarCommodityId,
                                          @RequestParam("nkName") String nkName) {
        //查存在
        ModelAndView mv = new ModelAndView();
        UserShoppingCar userShoppingCar = shoppingCarService.queryById(userCarCommodityId);
        mv.setViewName("redirect:/collections?nkName=" + nkName);
        mv.addObject("userName", nkName);
        if (null == userShoppingCar) {
            return mv;
        }
        User user = userService.selectByNkname(nkName);
        boolean b = shoppingCarService.deleteByUserIdAndCommodity(user.getId(), userShoppingCar.getCommodityId(), userShoppingCar.getCommodityPro());
        UserImg userImg = userImgService.queryById(user.getId());
        mv.addObject("userImg", userImg.getUserImg());
        return mv;
    }

    @RequestMapping("/collections/pay")
    public Result payAll(@RequestParam(value = "isAll", defaultValue = "false",required = false)boolean isAll,
                         @RequestParam(value = "ids", required = false)String ids,
                         @RequestParam("nkName") String nkName){
        if (isAll){

        }
        return null;
    }

}
