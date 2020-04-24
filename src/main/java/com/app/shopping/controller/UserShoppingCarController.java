package com.app.shopping.controller;

import com.app.shopping.mapper.CommodityMapper;
import com.app.shopping.mapper.InventoryMapper;
import com.app.shopping.model.User;
import com.app.shopping.model.entity.*;
import com.app.shopping.model.vo.PageVo;
import com.app.shopping.model.vo.ShoppingCarVo;
import com.app.shopping.service.*;
import com.app.shopping.util.OrderState;
import com.app.shopping.util.Result;
import com.app.shopping.util.ResultCode;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    OrderService orderService;
    @Autowired
    MyConsigneeService consigneeService;

    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    InventoryMapper inventoryMapper;

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
            shoppingCarVoList.add(new ShoppingCarVo(inventory.getId(), commodity.getImg(), commodity.getName(), e.getCommodityPro(),
                    inventory.getMoney(), inventory.getActivityMoney(), e.getSum(), format));
        });
        UserImg userImg = userImgService.queryById(user.getId());
        List<UserConsignee> consignees = consigneeService.queryByUserId(user.getId());
        mv.addObject("shoppingCarVoList", shoppingCarVoList);
        mv.addObject("consignees", consignees);
        mv.addObject("userImg", userImg.getUserImg());
        mv.addObject("user", user);
        mv.setViewName("collections");
        return mv;
    }

    /**
     * 加入购物车
     *
     * @param commodityId
     * @param commodityPro
     * @param sum
     * @param nkName
     * @return
     */
    @RequestMapping("/collections/add-into")
    @ResponseBody
    public Result addIntoShoppingCar(@RequestParam("commodityId") long commodityId,
                                     @RequestParam("commodityPro") String commodityPro,
                                     @RequestParam("sum") int sum,
                                     @RequestParam("nkName") String nkName) {
        //查库存
        Inventory inventory = inventoryService.queryByCommodityIdAndPro(commodityId, commodityPro);
        if (inventory.getInventory() < sum) {
            return Result.failed(ResultCode.KC_BZ);//不足
        }
        Commodity commodity = commodityService.queryById(commodityId);
        User user = userService.selectByNkname(nkName);
        UserShoppingCar insert = shoppingCarService.insert(new UserShoppingCar(user.getId(), commodityId, commodityPro, sum));
        return Result.success(ResultCode.JR_GWC_S);
    }

    @RequestMapping("/collections/remove")
    @ResponseBody
    public Result removeShoppingCar(@RequestParam("userCarCommodityId") long userCarCommodityId,
                                    @RequestParam("nkName") String nkName) {
        //查存在
        UserShoppingCar userShoppingCar = shoppingCarService.queryById(userCarCommodityId);
        if (null == userShoppingCar) {
            return Result.failed(ResultCode.SP_BCZ);
        }
        User user = userService.selectByNkname(nkName);
        boolean b = shoppingCarService.deleteByUserIdAndCommodity(user.getId(), userShoppingCar.getCommodityId(), userShoppingCar.getCommodityPro());
        return Result.success(ResultCode.YC_GWC_S.getMessage());
    }

    @RequestMapping("/collections/creatOrder")
    @ResponseBody
    @Transactional
    public Result payAll(@RequestParam(value = "isAll", defaultValue = "false", required = false) boolean isAll,
                         @RequestParam(value = "ids", required = false) String ids,
                         @RequestParam(value = "consigneeId") long consigneeId,
                         @RequestParam("nkName") String nkName) {
        User user = userService.selectByNkname(nkName);
        boolean re = true;
        List<Long> orderIds = new ArrayList<>();
        if (isAll) {//清空//
            List<UserShoppingCar> userShoppingCars = shoppingCarService.queryByUserId(user.getId());
            userShoppingCars.forEach(e -> {
                Result result = orderService.creatOrder(e.getSum(), e.getCommodityId(), e.getCommodityPro(), user, consigneeId);
            });
            return Result.success();
        } else {
            //下单的购物车商品id
            String[] split = StringUtils.split(ids, ",");
            for (int i = 0; i < split.length; i++) {
                String id = split[i];
                UserShoppingCar userShoppingCar = shoppingCarService.queryById(Long.valueOf(id));
                Result result = orderService.creatOrder(userShoppingCar.getSum(), userShoppingCar.getCommodityId(),
                        userShoppingCar.getCommodityPro(), user, consigneeId);
                if (200 != result.getCode()) {
                    re = false;
                }
                orderIds.add((Long) result.getData());
            }
            StringBuilder idsStr = new StringBuilder();
            orderIds.forEach(e ->{
                idsStr.append(e).append(",");
            });
            return re ? Result.success(idsStr,ResultCode.CREAT_ORDER_F.getMessage()) :
                    Result.response(orderIds, ResultCode.CREAT_ORDER_F.getMessage(), ResultCode.CREAT_ORDER_F.getCode());
        }
    }



}
