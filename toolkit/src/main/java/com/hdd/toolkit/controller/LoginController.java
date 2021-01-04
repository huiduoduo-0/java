package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

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
    public StatusResult doLogin(User user){
        //登录校验
       User user1 = userService.selectByUser(user);
        if (user1 != null){
            //生成token
            String token = UUID.randomUUID()+"";
            System.out.println("token令牌"+token);
            //存入redis

            //登录成功返回状态
            return new StatusResult(200,"登陆成功");
        }
          //登陆失败返回状态
        return new StatusResult(204,"登陆失败");
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
