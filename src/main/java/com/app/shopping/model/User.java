package com.app.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  {
    private String nkName;
    private String name;
    private String passWord;
    private String phone;
    private String eMail;
    private Long id;
    private Date creatTime;
    private Date updateTime;
    private Integer isDel;
}
