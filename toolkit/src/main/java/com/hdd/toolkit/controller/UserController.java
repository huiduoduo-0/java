package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import org.apache.commons.lang3.StringUtils;
import com.hdd.toolkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户
 */
@CrossOrigin
@RestController
@RequestMapping("/index")
public class UserController {

    @Resource
    UserService userService;

    @Autowired
    UserService userServiceImpl;
    /**
     * 用户个人信息
     * @return
     */
    @PostMapping(value="/usercenter")
    public StatusResult userCenter(Long id,String token){
        //返回并调用查询所有的方法
        return userService.SelectAll(id,token);
    }

    /**
     * 修改个人信息的方法
     * @param user
     * @param req
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping(value = "updateUserCenter")
    public StatusResult UpdateUserCenter(User user, HttpServletRequest req, MultipartFile file) throws IOException {
        //返回并调用修改信息的方法
        return  userService.doUpdate(user,req,file);
    }

    /**
     * 查询用户名是否重复的方法
     * @param user
     * @return
     */
    @PostMapping(value = "selectUserName")
    public StatusResult SelectUsername(User user){
        //返回并调用查询用户名是否重复的方法
        return  userService.SelectUserName(user);
    }

}
