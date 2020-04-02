package com.app.shopping.model;

import lombok.Data;

import java.util.Date;

@Data
public class ModelObject {
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;
}
