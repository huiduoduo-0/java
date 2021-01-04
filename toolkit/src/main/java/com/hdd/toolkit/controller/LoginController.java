package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 登录
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     * 执行登录方法
     * @return
     */
    @GetMapping("/go")
    public StatusResult doLogin(User user){
        //登录校验

        //登陆失败返回状态

        //登录成功返回状态
        return new StatusResult(200,"登陆成功");

    }

    /**
     * 忘记密码
     * @return
     */
    @PutMapping("/modify")
    public StatusResult forget(User user){
        //根据id查找用户

        //修改密码

        //修改失败返回状态

        //修改密码成功返回状态
        return new StatusResult(200,"修改成功");
    }

}
