package com.hdd.toolkit.dao;

import com.hdd.toolkit.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    /**
     * 根据id删除的方法
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加用户的方法
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 动态添加用户的方法
     *
     * @param user
     * @return
     */
    int insertSelective(User user);

    /**
     * 根据id查询用户的方法
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 动态修改用户的方法
     *
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * 修改用户的方法
     *
     * @param user
     * @return
     */
    int updateByPrimaryKey(User user);

    /**
     * 查询用户名是否重复的方法
     *
     * @param userName
     * @return
     */
    User repeatByUserName(@Param(value = "userName") String userName);

    User selectByUserName(User user);
}