package com.app.shopping.model;

import lombok.Data;

@Data
public class User extends ModelObject {
    private String nkName;
    private String name;
    private String passWord;
    private String phone;
    private String eMail;
}
