package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public StatusResult repeatUserName(@RequestParam("userName") String userName, @RequestParam("id") String id) {
        System.out.println(userName);
        //调用用户名重复的service方法
        return userService.repeatByUserName(userName, id);
    }

    /**
     * 手机号重复的方法
     *
     * @param mobile
     * @return
     */
    @GetMapping(value = "repeatMobile")

    public StatusResult repeatMobile(@RequestParam("mobile") String mobile){
        System.out.println(mobile);
        //调用手机号重复的service的方法
        return  userService.repeatByMobile(mobile);
    }

    /**
     * 邮箱重复的方法
     *
     * @param email
     * @return
     */
    @GetMapping(value = "repeatEmail")

    public StatusResult repeatEmail(@RequestParam("email") String email) {
        //调用邮箱重复的service的方法
        return userService.repeatByEmail(email);
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
        System.out.println(map);
        //调用执行登录的service的方法
        return userService.selectByUserName(map);
    }

    /**
     * 注销登录的方法
     *
     * @param token
     * @return
     */
    @PostMapping(value = "loginOut")
    public StatusResult loginOut(@RequestParam("token") String token) {
        //调用注销登录的service方法
        return userService.loginOut(token);
    }


    /**
     * 验证手机号用户名是否正确
     *
     * @param map
     * @return
     */
    @GetMapping(value = "selectByUserNameAndMobile")
    public StatusResult selectByUserNameAndMobile(@RequestBody Map<String, Object> map) {
        //调用查询手机号和用户名的方法
        return userService.selectByUserNameAndMobile(map);
    }

    /**
     * 忘记密码的方法
     *
     * @param map
     * @return
     */
    @PostMapping(value = "forgetUserPassword")
    public StatusResult forgetUserPassword(@RequestBody Map<String, Object> map) {
        //调用service的根据id查询用户的业务逻辑层的方法
        return userService.selectByPrimaryKey(map);
    }

    /**
     * 跳转个人中心的方法
     *
     * @return
     */
    @GetMapping(value = "userCenter")
    public StatusResult userCenter(@RequestBody Map<String, Object> map) {
        //调用个人中心的service的方法
        return userService.selectUserAndAccount(map);
    }
}
