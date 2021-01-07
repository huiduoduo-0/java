package com.hdd.toolkit.service;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface UserService {
    StatusResult selectByUser(User user);

    /**
     * 查询用户名重复的方法
     * @param user
     * @return
     */
    StatusResult selectByUserName(User user);

    int insert(User user);

    /**
     * 根据用户id查询所有的用户方法的业务逻辑层
     * @param id
     * @return
     */
    StatusResult SelectAll(Long id,String token);

    /**
     * 根据id查询出对象
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id,String token);

    /**
     * 执行修改个人信息的业务逻辑层
     * @param user
     * @return
     */
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
    boolean selectName(User user);
    //前台 电话查重
    boolean selectTel(User user);
    //前台 邮箱查重
    boolean selectEmail(User user);

}
