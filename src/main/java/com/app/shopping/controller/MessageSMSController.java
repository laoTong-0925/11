package com.app.shopping.controller;

import com.app.shopping.service.message.SMSService;
import com.app.shopping.util.CodeUtil;
import com.app.shopping.util.Result;
import com.app.shopping.util.ResultCode;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
public class MessageSMSController {

    private static final String PHONEKING = "17633464877";

    @Autowired
    SMSService smsService;

    @Autowired
    CodeUtil codeUtil;

    @RequestMapping("send-sms-code")
    @ResponseBody
    public Result sendVerifyCode(@RequestParam("phoneNum") String phoneNum,
                                 @RequestParam("businessTime") Long businessTime) {
        log.info("sendVerifyCode() 参数phoneNum：{} businessTime：{}", phoneNum, businessTime);
        if (phoneNum.equals(PHONEKING))
            return Result.response(null, ResultCode.SMS_SEND_SUCCESS.getMessage(), ResultCode.SMS_SEND_SUCCESS.getCode());
        String code = codeUtil.creatCode();
        return smsService.send(phoneNum, code, businessTime);
    }
}
