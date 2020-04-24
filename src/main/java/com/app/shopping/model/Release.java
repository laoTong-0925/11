package com.app.shopping.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Release {
    private String pro;
    private String money;
    private int sum;

    public Release(String pro, String money, int sum) {
        this.pro = pro;
        this.money = money;
        this.sum = sum;
    }
}
