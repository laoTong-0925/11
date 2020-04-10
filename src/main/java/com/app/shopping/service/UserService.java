package com.app.shopping.service;

import com.app.shopping.model.User;
import com.app.shopping.util.Result;

public interface UserService {

    boolean userIsExistByNkName(String nkName);

    boolean userIsExistByPhone(String phone);

    User selectByPhone(String phone);

    Result register(String nkName, String phone, String passWord, String vCode);

    Result perfectInfo(String nkName, String name, String eMail);

    boolean verifyAccount(String nkName, String phone, String passWord);

}
