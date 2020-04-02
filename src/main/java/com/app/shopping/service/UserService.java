package com.app.shopping.service;

import com.app.shopping.model.User;

public interface UserService {

    boolean userIsExistByNkName(String nkName);
    boolean userIsExistByPhone(String phone);

    User selectByPhone(String phone);

}
