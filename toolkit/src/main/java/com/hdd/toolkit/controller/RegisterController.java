package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import com.hdd.toolkit.utils.RandomValidateCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 注册
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    UserService userService;


    /**
     * 验证码
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping("/img")
    public void image(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getValidateCode(request, response);// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行注册方法
     * @return
     */
    @RequestMapping(value = "/go",method = RequestMethod.POST)
    public StatusResult doRegister(HttpSession session, @RequestBody User user){
        //注册校验
        User user1 = userService.selectByUserName(user);
        if (user1!=null){
            return new StatusResult(404,"该用户名已存在");
        }
        // 密码正则表达式
        String passwordpatter = "[0-9a-zA-Z]{6,20}";
        if (user.getUserPassword().matches(passwordpatter) == false){
            return new StatusResult(404,"密码格式不对");
        }
        // 手机号正则表达式
        String telpatter = "[1][0-9]{10}";
        if (user.getTel().matches(telpatter) == false){
            return new StatusResult(404,"请输入11位手机号");
        }
        String code1 = (String) session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
        if (!user.getCode().equalsIgnoreCase(code1)){
            return new StatusResult(404,"验证码不正确");
        }
        User user2 = new User();
        user2.setUserName(user.getUserName());
        user2.setUserPassword(user.getUserPassword());
        user2.setEmail(user.getEmail());
        user2.setTel(user.getTel());
        //注册成功返回状态
        userService.insert(user2);
        return new StatusResult(200,"注册成功");
    }
}
