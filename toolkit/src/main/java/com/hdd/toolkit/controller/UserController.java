package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户
 */
@CrossOrigin
@RestController
@RequestMapping("/index")
public class UserController {

    @Autowired
    UserService userServiceImpl;
    /**
     * 用户个人信息
     * @return
     */
    @RequestMapping("/usercenter")
    public Map<String,Object> userCenter(@RequestBody Map<String , Object> result){
        System.out.println(result);
        Map<String,Object> map = new HashMap<>();

        return map;
    }


}
