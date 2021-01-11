package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 用户
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 用户名重复的方法
     *
     * @param userName
     * @return
     */
    @GetMapping(value = "repeatUsername")
    public StatusResult repeatUserName(String userName, String id) {
        //调用用户名重复的service方法
        return userService.repeatByUserName(userName, id);
    }

    /**
     * 执行注册的方法
     *
     * @return
     */
    @PostMapping(value = "doRegister")
    public StatusResult doRegister(@RequestBody Map<String, String> map) {
        //调用执行注册的service的方法
        System.out.println("map=====" + map);
        return userService.doRegister(map);
    }

    /**
     * 执行登录的方法
     *
     * @param map
     * @param
     * @return
     */
    @PostMapping(value = "doLogin")
    public StatusResult doLogin(@RequestBody Map<String, Object> map) {
        //调用执行登录的service的方法
        return userService.selectByUserName(map);
    }

}
