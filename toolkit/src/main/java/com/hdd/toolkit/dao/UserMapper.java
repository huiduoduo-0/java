package com.hdd.toolkit.dao;

import com.hdd.toolkit.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

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

    /**
     * 登录查询用户名
     *
     * @param user
     * @return
     */
    User selectByUserName(User user);

    /**
     * 查询手机号是否重复的方法
     *
     * @param mobile
     * @return
     */
    User repeatByMobile(@Param(value = "mobile") String mobile);

    /**
     * 查询邮箱重复的方法
     *
     * @param email
     * @return
     */
    User repeatByEmail(@Param(value = "email") String email);

    /**
     * 查询用户表关联账户表
     *
     * @return
     */
    List<User> selectUserAndAccount(@Param(value = "id") String id);

    /**
     * 忘记密码根据用户名和手机号查询该用户
     *
     * @param userName
     * @param mobile
     * @return
     */
    User selectByUserNameAndMobile(@Param(value = "userName") String userName, @Param(value = "mobile") String mobile);
}