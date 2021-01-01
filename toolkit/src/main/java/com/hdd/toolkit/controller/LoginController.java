package com.hdd.toolkit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    /**
     * 执行登录方法
     * @return
     */
    @RequestMapping("/go")
    public String doLogin(){

        return  "";
    }

}
