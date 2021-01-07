package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import com.hdd.toolkit.utils.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    UserService userService;

    /**
     * 执行登录方法
     * @return
     */
    @PostMapping("/go")
    public StatusResult<Map> doLogin(@RequestBody User user){
        System.out.println("user====="+user);
        return userService.selectByUser(user);
    }

    /**
     * 忘记密码
     * @return
     */
    @PutMapping("/modify")
    public StatusResult<Map> forget(@RequestBody User user){

        return userService.forget(user);
    }

}
