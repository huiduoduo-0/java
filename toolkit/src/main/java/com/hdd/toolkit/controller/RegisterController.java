package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import com.hdd.toolkit.utils.RandomValidateCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 注册
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    UserService userService;

    /**
     * 执行注册方法
     * @return
     */
    @RequestMapping(value = "/go",method = RequestMethod.POST)
    public StatusResult doRegister(@RequestBody User user){

        return userService.selectByUserName(user);
    }
}
