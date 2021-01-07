package com.hdd.toolkit.dao;

import com.hdd.toolkit.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 新增用户信息的方法
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 动态添加用户信息的方法
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据id查询所有的方法
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);
    /**
     * 动态修改用户的方法
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 修改用户的方法
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    User selectByUser(User user);

    User selectByUserName(User user);

    /**
     * 根据用户id查询所有的用户方法
     * @param id
     * @return
     */
    List<User> SelectAll(Long id);


    User selectTel(User user);

    User selectEmail(User user);

    void updateByUsername(User user1);
}