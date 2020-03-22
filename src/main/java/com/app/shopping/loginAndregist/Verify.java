package com.app.shopping.loginAndregist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class Verify {
//    @ApiOperation(value = "获取图形验证码")
    @GetMapping("/test/img/{macCode}")
    public byte[] getByte(@PathVariable String macCode){
        Object[] objs = VerifyUtil.createImage();
        String randomStr = (String) objs[0];
//        log.info("randomStr  result: "+randomStr);
//        redisService.delete("USER:IMG:" + macCode);
//        redisService.save("USER:IMG:" + macCode, randomStr);  // 存储到redis中，后续用于作验证
        return (byte[]) objs[1];
    }

}
