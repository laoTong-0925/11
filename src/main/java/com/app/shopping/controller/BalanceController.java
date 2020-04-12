package com.app.shopping.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
public class BalanceController {
    /**
     * 余额
     *
     * @return
     */
    @RequestMapping("/balance")
    public String balance() {
        return "balance";
    }
}
