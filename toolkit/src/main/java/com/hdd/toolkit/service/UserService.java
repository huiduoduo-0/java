package com.hdd.toolkit.service;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public interface UserService {

    /**
     * 查询用户名是否重复的方法的业务逻辑层
     * @param userName
     * @return
     */
    StatusResult repeatByUserName(@Param(value ="userName") String userName,@Param(value ="id")String id);

    /**
     * 注册用户的方法的业务逻辑层
     * @return
     */
    StatusResult doRegister(Map<String,String> map);

    /**
     * 登录时查询用户名是否重复的业务逻辑层
     * @param user
     * @return
     */
    StatusResult selectByUserName(Map<String,Object> map);
    StatusResult doUpdate(User user, HttpServletRequest req, MultipartFile file) throws IOException;

    /**
     * 查找用户名重复的方法的业务逻辑层
     * @param user
     * @return boolean
     */
    StatusResult SelectUserName(User user);

    //忘记密码
    StatusResult forget(User user);

    //前台 用户名查重
    StatusResult selectName(User user);
    //前台 电话查重
    StatusResult selectTel(User user);
    //前台 邮箱查重
    StatusResult selectEmail(User user);



}
