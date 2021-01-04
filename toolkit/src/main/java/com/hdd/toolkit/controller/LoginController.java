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
    @Resource
    RedisTemplate redisTemplate;

    Map<String,Object> map = new HashMap<>();

    /**
     * 执行登录方法
     * @return
     */
    @PostMapping("/go")
    public StatusResult<Map> doLogin(@RequestBody User user){
        //登录校验
        try {
            User userDB = userService.selectByUser(user);
            Map<String, String> payload = new HashMap<>();
            payload.put("id", userDB.getId().toString());
            payload.put("userName", userDB.getUserName());
            //生成token
            String token = JwtUtil.getToken(payload);
            map.put("token",token);
            //存入redis
            redisTemplate.opsForValue().set("token",token,7, TimeUnit.DAYS);
            //登录成功返回状态
            return new StatusResult<Map>(200,"登陆成功",map);
        }catch (Exception e){

        }
        return new StatusResult<Map>(500,"登录失败");
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
