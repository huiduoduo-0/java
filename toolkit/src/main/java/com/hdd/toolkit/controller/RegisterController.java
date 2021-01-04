package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 注册
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    /**
     * 执行注册方法
     * @return
     */
    @RequestMapping("/go")
    public StatusResult doRegister(User user){
         //注册校验

        //注册失败返回状态

        //注册成功返回状态
        return new StatusResult(200,"注册成功");
    }
}
