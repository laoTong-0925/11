package com.app.shopping.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    int selectCount();
}
