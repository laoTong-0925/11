package com.app.shopping.service.message.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.app.shopping.cache.JedisUtil;
import com.app.shopping.mapper.SMSRecordLogMapper;
import com.app.shopping.mapper.SMSRecordMapper;
import com.app.shopping.mapper.SMSVCodeMapper;
import com.app.shopping.model.msg.SmsRecord;
import com.app.shopping.model.msg.SmsRecordLog;
import com.app.shopping.model.msg.SmsVCode;
import com.app.shopping.service.UserService;
import com.app.shopping.service.message.SMSService;
import com.app.shopping.util.*;
import com.app.shopping.util.Constant.SystemConstant;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Log4j2
@Service
public class SMSServiceImpl implements SMSService {

    @Autowired
    SMSRecordMapper smsRecordMapper;

    @Autowired
    SMSRecordLogMapper logMapper;

    @Autowired
    SMSVCodeMapper smsvCodeMapper;

    @Autowired
    UserService userService;

    @Autowired
    AlibbResponseUtil alibbResponseUtil;
    @Autowired
    DateUtil dateUtil;
    @Autowired
    JedisUtil jedisUtil;

    @Override
    public Result send(String to, String subject, String text) {

        return null;
    }

    @Override
    public Result send(String toNum, String code, long businessTime) {
        SmsRecord record = new SmsRecord();
        SmsRecordLog logR = new SmsRecordLog();
        sendBefore(record, logR, toNum, code, businessTime);
        DefaultProfile profile = DefaultProfile
                .getProfile(SystemConstant.ALIBB_REGIONID, SystemConstant.ALIBB_ACCESSKEYID, SystemConstant.ALIBB_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        String codeNum = "{\"code\":\"" + code + "\"}";

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(SystemConstant.ALIBB_DOMAIN);
        request.setVersion(SystemConstant.ALIBB_VERSION);
        request.setAction(SystemConstant.ALIBB_ACTION);
        request.putQueryParameter(SystemConstant.ALIBB_KEY_REGIONID, SystemConstant.ALIBB_REGIONID);
        request.putQueryParameter(SystemConstant.ALIBB_KEY_PHONE_NUM, toNum);
        request.putQueryParameter(SystemConstant.ALIBB_KEY_SIGN_NAME, SystemConstant.ALIBB_SIGN_VAL);
        request.putQueryParameter(SystemConstant.ALIBB_KEY_TEMPLATE_CODE, SystemConstant.ALIBB_KEY_TEMPLATE_CODE_VAL);
        request.putQueryParameter(SystemConstant.ALIBB_KEY_TEMPLATE_PARAM, codeNum);
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("号码:{} 短信服务返回结果:{}", toNum, response.getData());
            String message = alibbResponseUtil.getCommonResponseMessage(response);
            sendAfter(record, logR, message, response.getData());
            if (message.equals("OK") || message.equals("账户余额不足")) {
//            if (message.equals("OK")){
                String setex = jedisUtil.setex(SystemConstant.REDISVTAG + toNum, code, SystemConstant.EXPIRETIME / 1000);
                log.info("setex验证码:{}", setex);
                if (Strings.isBlank(setex)) {//redis设置失败
                    smsRecordMapper.updateStateById(record.getId(), Sms.SMS_FI_R.getCode());
                    return Result.response("redis设置验证码为空", ResultCode.SMS_SEND_FAILED.getMessage(), ResultCode.SMS_SEND_FAILED.getCode());
                }
                return Result.response(null, ResultCode.SMS_SEND_SUCCESS.getMessage(), ResultCode.SMS_SEND_SUCCESS.getCode());
            } else
                return Result.response(null, ResultCode.SMS_SEND_FAILED.getMessage(), ResultCode.SMS_SEND_FAILED.getCode());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return Result.failed();
    }

    /**
     * 发送前的 入库
     *
     * @param toNum
     * @param code
     */
    private void sendBefore(SmsRecord record, SmsRecordLog logR, String toNum, String code, long businessTime) {
        //插入record
        Date date = new Date(businessTime);
        record.build(toNum, Sms.SMS_v.getCode(), date);
        int recordR = smsRecordMapper.insertSMS(record);
        if (recordR != 0) {
            log.info("sendBefore() 插入record成功 :{}", record);
        } else {
            log.info("sendBefore() 插入失败");
        }

        //插入log
        logR.setSmsRecordId(record.getId());
        int logI = logMapper.insert(logR);
        if (logI != 0) {
            log.info("sendBefore() 插入SmsRecordLog成功 :{}", logR);
        } else {
            log.info("sendBefore() 插入SmsRecordLog失败");
        }

        //插入smsv
        SmsVCode smsVCode = new SmsVCode();
        setExpireTime(smsVCode, date);
        smsVCode.setCode(code);
        smsVCode.setExpireTime(DateUtil.setExpireTime(date));
        smsVCode.setSmsRecordId(record.getId());
        int smsVR = smsvCodeMapper.insert(smsVCode);
        if (smsVR != 0) {
            log.info("sendBefore() 插入SmsVCode成功 :{}", smsVR);
        } else {
            log.info("sendBefore() 插入SmsVCode失败");
        }

        //设置关联外键
        int i = smsRecordMapper.setLogIdAndSmsVIdById(record.getId(), logR.getId(), smsVCode.getId());
        int i1 = smsvCodeMapper.setRecordIdById(smsVCode.getId(), record.getId());
        int i2 = logMapper.setRecordIdById(logR.getId(), record.getId());
        log.info("smsRecordMapper.setLogIdAndSmsVIdById   smsvCodeMapper.setRecordIdById  " +
                "logMapper.setRecordIdById\n结果：{} ：{} ：{}", i, i1, i2);
    }

    /**
     * 发送完成后，修改消息状态，log状态
     *
     * @param message
     */
    private void sendAfter(SmsRecord record, SmsRecordLog logR, String message, String date) {
        int state;
//        if (message.equals("OK")) {
        if (message.equals("OK") || message.equals("账户余额不足")) {
            state = Sms.SMS_SU.getCode();
        } else {
            state = Sms.SMS_FI.getCode();
        }
        //更新sms_record
        int i1 = smsRecordMapper.updateStateById(record.getId(), state);
        //更新log
        int i = logMapper.setMessageById(logR.getId(), date);

        log.info("smsRecordMapper.updateStateById结果：{} ，logMapper.setMessageById结果：{}", i1, i);
    }

    /**
     * 设置过期时间
     *
     * @param smsVCode
     */
    private void setExpireTime(SmsVCode smsVCode, Date businessTime) {
        Date expireDate = DateUtil.setExpireTime(businessTime);
        smsVCode.setExpireTime(expireDate);
    }

    /**
     * 查redis 验证码是否存在
     * 不存在 不相等 返回false
     *
     * @param phone
     * @param code
     * @return
     */
    @Override
    public boolean vCodeIsExpire(String phone, String code) {
        String s = jedisUtil.get(SystemConstant.REDISVTAG + phone, SystemConstant.REDISVTAG_DATAINDEX);
        return !Strings.isBlank(s) && code.equals(s);
    }
}
