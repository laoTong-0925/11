package com.app.shopping.service.impl;

import com.app.shopping.mapper.*;
import com.app.shopping.model.User;
import com.app.shopping.model.entity.ActivityUser;
import com.app.shopping.model.entity.Commodity;
import com.app.shopping.model.entity.Inventory;
import com.app.shopping.model.entity.UserConsignee;
import com.app.shopping.service.GetActivityUserService;
import com.app.shopping.service.OrderService;
import com.app.shopping.service.message.SMSService;
import com.app.shopping.util.DateUtil;
import com.app.shopping.util.OrderState;
import com.app.shopping.util.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Log4j2
public class GetActivityUserServiceImpl implements GetActivityUserService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ActivityUserMapper activityUserMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SMSService smsService;
    @Autowired
    private MyConsigneeMapper consigneeMapper;
    private static final Integer ACTIVITY_INVENTORY = 10;
    private static final Integer ACTIVITY_SUM = 1;

    @Override
    @Transactional
    public void getActivityUserByEndTime() {
        //查活动
        Date aStartTime = DateUtil.getAStartTimeTest();//测试
//        Date aStartTime = DateUtil.getAStartTime();
        List<Commodity> activity = commodityMapper.getACommodityByEndTimeAndIsT(aStartTime);
        //循环查对应活动中奖的人
        activity.forEach(e -> {
            List<ActivityUser> activityUsers = activityUserMapper.queryByCid(e.getId());
            if (!ListUtils.isEmpty(activityUsers)) {
                ActivityUser luckyDog = getLuckyDog(activityUsers);
                User user = userMapper.selectById(luckyDog.getUserId());
                if (null != luckyDog) {
                    log.info("{}活动 中奖幸运儿！！  {}", e.toString(), luckyDog.getId());
                    //加10库存 防止没货
                    int i = inventoryMapper.updateInventoryByCId(ACTIVITY_INVENTORY, e.getId());
                    //查商品所有属性
                    List<Inventory> inventories = inventoryMapper.queryByCId(e.getId());
                    Commodity commodity = commodityMapper.queryById(e.getId());
                    //收货地址
                    UserConsignee userConsignee = consigneeMapper.queryByUserId(luckyDog.getUserId()).get(0);
                    List<Long> orderIds = new ArrayList<>();
                    inventories.forEach(v -> {
                        //订单产生
                        Result result = orderService.creatOrder(ACTIVITY_SUM, e.getId(), v.getProperty(),
                                user, userConsignee.getId());
                        if (200 == result.getCode()){
                            Long data = (long) result.getData();
                            orderIds.add( data);
                        }

                    });
                    //修改订单状态，支付金额为0
                    Result result = orderService.luckyDog(orderIds, OrderState.CREAT_PAY.getCode(), user.getId());
                    //发短信
                    String luckyText = getLuckyText(commodity, user);
                    //改活动状态
                    int i1 = commodityMapper.updateIsTById(commodity.getId(), 0);
//                Result send = smsService.send(user.getPhone(), luckyText, System.currentTimeMillis());
                }
            }
        });
    }

    /**
     * 中奖文案
     */
    private String getLuckyText(Commodity commodity, User user) {
        String luckyText = user.getNkName() + "尊敬的用户，您好！！！\n" +
                "您昨日参加的商品《" + commodity.getName() + "》抽奖活动已经开奖，恭喜您中奖！！！\n" +
                "稍后我们会将奖品发至您的默认收货地址。\n感谢您的参与。\n祝您生活愉快！！！";
        return luckyText;
    }

    /**
     * 抽奖一个用户
     */
    private ActivityUser getLuckyDog(List<ActivityUser> activityUserList) {
        if (ListUtils.isEmpty(activityUserList))
            return null;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int i = random.nextInt(activityUserList.size());
        if (i >= 0)
            return activityUserList.get(i);
        return null;
    }

    public static void main(String[] args) throws Exception {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        while (true) {
            int i = random.nextInt(5);
            System.out.println(i);
            Thread.sleep(400);
        }
    }
}
