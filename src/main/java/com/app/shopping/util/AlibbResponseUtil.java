package com.app.shopping.util;

import com.aliyuncs.CommonResponse;
import org.springframework.stereotype.Component;

@Component
public class AlibbResponseUtil {

    public String getCommonResponseMessage(CommonResponse response) {
        String data = response.getData();
        String[] split = data.split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains("Message")){
                String[] as = split[i].split(":");
                return as[1].substring(1,as[1].length()-1);
            }
        }
        return "";
    }

}
