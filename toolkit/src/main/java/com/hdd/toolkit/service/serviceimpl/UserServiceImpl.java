package com.hdd.toolkit.service.serviceimpl;

import com.hdd.toolkit.dao.UserMapper;
import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import com.hdd.toolkit.utils.JwtUtil;
import com.hdd.toolkit.utils.MD5ShiroUtil;
import com.hdd.toolkit.utils.RandomValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    HttpSession session;


    Map<String,Object> map = new HashMap<>();

    @Override
    public StatusResult<Map> selectByUser(User user) {
            //登录逻辑的根据用户名和密码查询
        User userDB = userMapper.selectByUserName(user);
        String pwd= userDB.getUserPassword();
        System.out.println("pwd = " + pwd);
        String md5To = MD5ShiroUtil.getMd5(userDB);
        System.out.println("md5To = " + md5To);
        if (userDB != null) {
            if (pwd.equals(md5To)){
                //用户不为空
                Map<String, String> payload = new HashMap<>();
                payload.put("id", userDB.getId().toString());
                payload.put("userName", userDB.getUserName());
                //生成token
                String token = JwtUtil.getToken(payload);
                map.put("token", token);
                //存入redis
                redisTemplate.opsForValue().set("token", token, 7, TimeUnit.DAYS);
                //登录成功返回状态
                return new StatusResult<Map>(200, "登陆成功", map);
            }
        }

        return new StatusResult<Map>(500,"登录失败");
    }


    /**
     * 注册逻辑的根据用户名查询
     */
    public StatusResult<Map> selectByUserName(User user) {
        //注册校验
        User user1 =userMapper.selectByUserName(user);
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
        long time = new Date().getTime();
        user2.setUserName(user.getUserName());
        user2.setUserPassword(MD5ShiroUtil.getMd5(user));
        user2.setSalt(user.getSalt());
        user2.setEmail(user.getEmail());
        user2.setTel(user.getTel());
        user2.setAddtime(time);
        //注册成功返回状态
        userMapper.insert(user2);
        return new StatusResult(200,"注册成功");
    }




    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
