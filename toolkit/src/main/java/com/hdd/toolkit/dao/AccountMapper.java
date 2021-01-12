package com.hdd.toolkit.dao;

import com.hdd.toolkit.model.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    int insert(Account record);

    int insertSelective(Account record);
}