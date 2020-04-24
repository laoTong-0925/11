package com.app.shopping.controller;

import com.app.shopping.mapper.ConsumeLogMapper;
import com.app.shopping.mapper.OrderMapper;
import com.app.shopping.mapper.UserInfoMapper;
import com.app.shopping.model.User;
import com.app.shopping.model.entity.ConsumeLog;
import com.app.shopping.model.entity.Order;
import com.app.shopping.model.entity.UserInfo;
import com.app.shopping.service.OrderService;
import com.app.shopping.service.UserInfoService;
import com.app.shopping.service.UserService;
import com.app.shopping.util.OrderState;
import com.app.shopping.util.Result;
import com.app.shopping.util.ResultCode;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@Controller
public class MyOrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ConsumeLogMapper consumeLogMapper;

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private OrderService orderService;

    /**
     * 订单
     *
     * @return
     */
    @RequestMapping("/myOrder")
    public ModelAndView myOrder(@RequestParam(value = "nkName") String nkName,
                                @RequestParam(value = "state", required = false) int state
//                                @RequestParam()
    ) {
        ModelAndView mv = new ModelAndView();
        User user = userService.selectByNkname(nkName);
        mv.addObject("userName", user.getNkName());
        mv.setViewName("myOrder");
        return mv;
    }

    @ResponseBody
    @RequestMapping("/order/creat")
    public Result pay(@RequestParam(value = "commodityId") int commodityId,
                      @RequestParam(value = "nkName") String nkName,
                      @RequestParam(value = "consigneeId") int consigneeId,
                      @RequestParam(value = "sum") int sum,
                      @RequestParam(value = "properties") String properties
    ) {
        User user = userService.selectByNkname(nkName);
        Result result = orderService.creatOrder(sum, commodityId, properties, user, consigneeId);
        if (null != result.getData()){
            String orderId = "," + result.getData();
            return Result.success(orderId);
        }
        return Result.failed();
    }

    @ResponseBody
    @RequestMapping("/order/pay")
    @Transactional
    public Result pay(@RequestParam(value = "orderIds") String orderIds,
                      @RequestParam(value = "nkName") String nkName,
                      @RequestParam(value = "passWord") String password) {
        List<Long> ids = changeOrderIds(orderIds);
        User user = userService.selectByNkname(nkName);
        UserInfo userInfo = userInfoService.queryByUserId(user.getId());
        //比较密码
        if (StringUtils.isBlank(password) && !password.equals(userInfo.getPayPass())) {
            return Result.failed("密码错误");
        }
        List<Order> orders = orderMapper.queryByStateAndId(OrderState.CREAT_NOT_PAY.getCode(), ids);
        List<Double> moneySum = new ArrayList<>();
        double sum = 0.0;
        orders.forEach(e -> moneySum.add(Double.parseDouble(e.getMoney()) * (double) e.getCSum()));
        for (Double d : moneySum) {
            sum = sum + d;
        }
        double balance = Double.parseDouble(userInfo.getBalance());
        if (sum > balance) {
            return Result.failed("金额不足");
        }
        String nowBalance = String.valueOf(balance - sum);
        //扣钱
        int i = userInfoMapper.updateMoney(user.getId(), nowBalance);
        //订单状态修改
        int i1 = orderMapper.updateStateAndPayByUserId(OrderState.CREAT_PAY.getCode(),
                sum + "", user.getId(),ids);
        //消费记录
        ArrayList<ConsumeLog> consumeLogs = new ArrayList<>();
        String consumeMoney = sum + "";
        orders.forEach(e -> consumeLogs.add(new ConsumeLog(user.getId(), e.getId(),
                Double.parseDouble(e.getMoney()) * (double) e.getCSum() + "")));
        int consumeLogsInTo = consumeLogMapper.insertBatch(consumeLogs);
        return Result.success();
    }

    private List<Long> changeOrderIds(String orderIds) {
        if (StringUtils.isNotBlank(orderIds)) {
            List<Long> ids = new ArrayList<>();
            String[] split = StringUtils.split(orderIds, ",");
            for (String orderId : split) {
                ids.add(Long.valueOf(orderId));
            }
            return ids;
        } else {
            return new ArrayList<>();
        }
    }


}
