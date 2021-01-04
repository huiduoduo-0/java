package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户
 */
@RestController
@RequestMapping("/index")
public class UserController {

    /**
     * 用户个人信息
     * @return
     */
    @RequestMapping("/usercenter")
    public Map<String,Object> userCenter(){
        Map<String,Object> map = new HashMap<>();

        return map;
    }


}
