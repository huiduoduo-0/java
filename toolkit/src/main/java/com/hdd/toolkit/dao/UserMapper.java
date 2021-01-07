package com.hdd.toolkit.dao;

import com.hdd.toolkit.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUser(User user);

    //根据用户名查重
    User selectByUserName(User user);

    //根据用户名修改
    int updateByUsername(User user);

    //根据手机号查重
    User selectTel(User user);

    //根据邮箱查重
    User selectEmail(User user);

}