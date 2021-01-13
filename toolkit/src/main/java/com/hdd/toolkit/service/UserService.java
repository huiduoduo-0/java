package com.hdd.toolkit.service;

import com.hdd.toolkit.model.StatusResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public interface UserService {

    /**
     * 查询用户名是否重复的方法的业务逻辑层
     *
     * @param userName
     * @return
     */
    StatusResult repeatByUserName(@Param(value = "userName") String userName, @Param(value = "id") String id);

    /**
     * 查询手机号是否重复的方法的业务逻辑层
     * @param mobile
     * @return
     */
    StatusResult repeatByMobile(@Param(value = "mobile") String mobile);

    /**
     * 查询邮箱重复的业务逻辑层
     * @param email
     * @return
     */
    StatusResult repeatByEmail(@Param(value = "email") String email);

    /**
     * 注册用户的方法的业务逻辑层
     *
     * @return
     */
    StatusResult doRegister(Map<String, String> map);

    /**
     * 登录查询用户名的业务逻辑层
     * @param map
     * @return
     */
    StatusResult selectByUserName(Map<String, Object> map);

    /**
     *退出登录的业务逻辑层
     * @return
     */
    StatusResult loginOut(String token);

    /**
     * 查询用户表关联账户表的业务逻辑层
     * @return
     */
    StatusResult selectUserAndAccount();




}
