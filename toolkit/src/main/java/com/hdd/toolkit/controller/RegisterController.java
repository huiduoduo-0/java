package com.hdd.toolkit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    /**
     * 执行注册方法
     * @return
     */
    @RequestMapping("/go")
    public String doRegister(){

        return "";
    }
}
