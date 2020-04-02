package com.app.shopping.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Log4j2
public class CodeUtil {

    private static final int CODELENGTH = 5;

    /**
     * 产生5位数字随机数
     *
     * @return String
     */
    public String creatCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODELENGTH; i++) {
            int codeN = ThreadLocalRandom.current().nextInt(0, 10);
            code.append(codeN);
        }
        log.info("产生5位数随机数 code:{}", code.toString());
        return code.toString();
    }

}
