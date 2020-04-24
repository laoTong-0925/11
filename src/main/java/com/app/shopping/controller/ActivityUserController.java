package com.app.shopping.controller;

import com.app.shopping.mapper.ActivityUserMapper;
import com.app.shopping.mapper.CommodityMapper;
import com.app.shopping.model.User;
import com.app.shopping.model.entity.ActivityUser;
import com.app.shopping.model.entity.Commodity;
import com.app.shopping.model.entity.UserShoppingCar;
import com.app.shopping.service.ActivityUserService;
import com.app.shopping.service.UserService;
import com.app.shopping.util.Result;
import com.app.shopping.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 参加活动用户(ActivityUser)表控制层
 *
 * @author makejava
 * @since 2020-04-21 18:29:13
 */
@RestController
public class ActivityUserController {
    /**
     * 服务对象
     */
    @Autowired
    private ActivityUserService activityUserService;

    @Autowired
    private UserService userService;
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private ActivityUserMapper activityUserMapper;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectOne")
    public ActivityUser selectOne(Long id) {
        return this.activityUserService.queryById(id);
    }

    /**
     * 用户参加抽将
     *
     * @param nkName
     * @param cId
     * @return
     */
    @RequestMapping("into-activityPoll")
    @ResponseBody
    public Result intoActivityPoll(@RequestParam("nkName") String nkName,
                                   @RequestParam("cId") int cId,
                                   @RequestParam("consignee")String consignee) {
        Commodity commodity = commodityMapper.queryById((long) cId);
        if (commodity.getAEndTime().getTime() < System.currentTimeMillis())
            return Result.failed("活动已经下线了");
        User user = userService.selectByNkname(nkName);
        ActivityUser activityUser = activityUserMapper.queryByCidAndUserId(cId, user.getId());
        if (null != activityUser){
            return Result.success(null,"您已经参加过了");
        }
        activityUserService.userIntoPool(user, (long) cId,consignee);

        return Result.success();
    }
}