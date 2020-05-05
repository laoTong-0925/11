package com.app.shopping.controller;

import com.app.shopping.mapper.CommodityMapper;
import com.app.shopping.mapper.ConsumeLogMapper;
import com.app.shopping.mapper.OrderMapper;
import com.app.shopping.mapper.UserInfoMapper;
import com.app.shopping.model.User;
import com.app.shopping.model.entity.Commodity;
import com.app.shopping.model.entity.ConsumeLog;
import com.app.shopping.model.entity.Order;
import com.app.shopping.model.entity.UserInfo;
import com.app.shopping.model.vo.OrderVo;
import com.app.shopping.service.OrderService;
import com.app.shopping.service.UserInfoService;
import com.app.shopping.service.UserService;
import com.app.shopping.util.OrderState;
import com.app.shopping.util.Result;
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
public class OrderController {

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
    @Autowired
    private CommodityMapper commodityMapper;


    /**
     * 订单
     *
     * @return
     */
    @RequestMapping("/myOrder")
    public ModelAndView myOrder(@RequestParam(value = "nkName") String nkName,
                                @RequestParam(value = "state", required = false) Integer state,
                                @RequestParam(value = "index", defaultValue = "0", required = false) int index) {
        ModelAndView mv = new ModelAndView();
        User user = userService.selectByNkname(nkName);
        List<Order> orders;
        List<OrderVo> vos = new ArrayList<>();
        if (null == state) {
            orders = orderService.findByUser(user, index * 10, (index + 1) * 10);
        } else {
            orders = orderService.findByStateAndUser(user, state, index * 10, (index + 1) * 10);
        }
        orders.forEach(e -> {
            Commodity commodity = commodityMapper.queryById(e.getCommodityId());
            double oneMoney = Double.parseDouble(e.getMoney()) / (double) e.getCSum();
            if (null != commodity) {
                vos.add(new OrderVo(e.getId(), e.getUserId(), e.getConsignee(),
                        e.getCommodityId(), e.getCSum(), e.getProperties(), commodity.getName(), e.getMoney(),
                        e.getState(), e.getExTime(), e.getIsTicket(), e.getPay(),
                        e.getCreatTime(), e.getUpdateTime(), oneMoney + "", commodity.getImg()));
            }

        });
        int count = orderMapper.queryCountByUserId(user.getId());
        mv.addObject("orderVos", vos);
        mv.addObject("userName", user.getNkName());
        mv.addObject("count", vos.size());
        mv.addObject("lastPage", vos.size() / 10);
        mv.addObject("index", index);
        mv.addObject("size", count);
        mv.setViewName("myOrder");
        return mv;
    }

    /**
     * 订单id查询
     *
     * @param id
     * @param nkName
     * @return
     */
    @RequestMapping("/order-search")
    public ModelAndView orderSearch(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "nkName") String nkName) {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isBlank(nkName)) {
            mv.setViewName("login");
            return mv;
        }
        if (id == 0)
            return null;
        Order e = orderMapper.queryById((long) id);
        List<OrderVo> vos = new ArrayList<>();
        Commodity commodity = commodityMapper.queryById(e.getCommodityId());
        double oneMoney = Double.parseDouble(e.getMoney()) / (double) e.getCSum();
        if (null != commodity) {
            vos.add(new OrderVo(e.getId(), e.getUserId(), e.getConsignee(),
                    e.getCommodityId(), e.getCSum(), e.getProperties(), commodity.getName(), e.getMoney(),
                    e.getState(), e.getExTime(), e.getIsTicket(), e.getPay(),
                    e.getCreatTime(), e.getUpdateTime(), oneMoney + "", commodity.getImg()));
        }
        User user = userService.selectByNkname(nkName);
        mv.addObject("orderVos", vos);
        mv.addObject("userName", user.getNkName());
        mv.addObject("count", 1);
        mv.addObject("size", 1);
        mv.setViewName("myOrder");
        return mv;
    }

    /**
     * 订单创建
     *
     * @param commodityId
     * @param nkName
     * @param consigneeId
     * @param sum
     * @param properties
     * @return
     */
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
        if (null != result.getData()) {
            String orderId = "," + result.getData();
            return Result.success(orderId);
        }
        return Result.failed();
    }

    /**
     * 手动取消订单
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/order/complete")
    public Result complete(Integer id) {
        if (null == id || 0 == id)
            return Result.failed();
        int i = orderMapper.updateStateById((long) id, OrderState.CANCEL.getCode());
        if (i != 0)
            return Result.success();
        return Result.failed();
    }


    /**
     * 删除订单
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/order/remove")
    public Result remove(Integer id) {
        if (null == id || 0 == id)
            return Result.failed();
        int i = orderMapper.remove((long) id);
        if (i != 0)
            return Result.success();
        return Result.failed();
    }
    /**
     * 完成
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/order/wc")
    public Result wc(Integer id) {
        if (null == id || 0 == id)
            return Result.failed();
        int i = orderMapper.updateStateById((long) id, OrderState.END.getCode());
        if (i != 0)
            return Result.success();
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
        int i1 = orderMapper.updateStateAndPayById(OrderState.CREAT_PAY.getCode(),
                sum + "", user.getId(), ids);
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
