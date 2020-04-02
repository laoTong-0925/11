package com.app.shopping.mapper;

import com.app.shopping.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectUserByNkName(@Param("nkName") String nkName);
    User selectUserByPhone(@Param("phone") String phone);
}
