package com.app.shopping.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ManageCommoditVo {
    private String name;
    private String detail;
    private String img;
    private Long cId;
    private List<String> pro;
    private List<String> money;
    private List<Integer> inventory;
    private String isT;//是 否

    public ManageCommoditVo(String name, String detail, String img,
                            List<String> pro, List<String> money,
                            List<Integer> inventory, String isT,long cId) {
        this.name = name;
        this.detail = detail;
        this.img = img;
        this.pro = pro;
        this.money = money;
        this.inventory = inventory;
        this.isT = isT;
        this.cId = cId;
    }
}
