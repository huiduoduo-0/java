package com.hdd.toolkit.service.impl;

import com.hdd.toolkit.dao.UserMapper;
import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import com.hdd.toolkit.utils.JedisPoolUtil;
import com.hdd.toolkit.utils.JwtUtil;
import com.hdd.toolkit.utils.MD5ShiroUtil;
import com.hdd.toolkit.utils.RandomValidateCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    HttpSession session;


    /**
     * 查询用户名是否重复的方法的业务实现方法
     *
     * @param userName
     * @return
     */
    @Override
    public StatusResult<Map> repeatByUserName(String userName, String id) {
        //调用查询用户名重复的方法
        User user = userMapper.repeatByUserName(userName);
        //进行用户名重复校验
        if (user != null) {
            //返回错误信息
            return new StatusResult<Map>(404, "用户名重复");
        } else {
            return new StatusResult<Map>(200, "用户名可用");
        }

    }

    /**
     * 查询手机号是否重复的业务实现方法
     *
     * @param mobile
     * @return
     */
    @Override
    public StatusResult repeatByMobile(String mobile) {
        //调用查询手机号重复的方法
        User user = userMapper.repeatByMobile(mobile);
        //进行手机号重复校验
        if (user != null) {
            //返回错误信息
            return new StatusResult<Map>(404, "手机号重复");
        } else {
            return new StatusResult<Map>(200, "手机号可用");
        }
    }

    /**
     * 查询邮箱重复的业务实现方法
     *
     * @param email
     * @return
     */
    @Override
    public StatusResult repeatByEmail(String email) {
        //调用查询邮箱重复的方法
        User user = userMapper.repeatByEmail(email);
        //进行邮箱重复校验
        if (user != null) {
            //返回错误信息
            return new StatusResult<Map>(404, "邮箱重复");
        } else {
            return new StatusResult<Map>(200, "邮箱可用");
        }
    }


    /**
     * 注册用户的方法的业务实现方法
     *
     * @param map
     * @return
     */
    @Override
    public StatusResult<Map> doRegister(Map<String, String> map) {
        //利用正则表达式来设置用户名格式
        String reUsername = "[0-9a-zA-Z]{4,12}";
        //进行用户名格式的校验
        if (!map.get("userName").matches(reUsername)) {
            return new StatusResult(404, "用户名格式错误");
        }
        //利用正则表达式来设置密码格式
        String rePassword = "[0-9a-zA-Z]{6,20}";
        //进行密码格式的校验
        if (!map.get("userPassword").matches(rePassword)) {
            return new StatusResult(404, "密码格式错误");
        }
        //校验密码和确认密码是否相同
        if (!map.get("ConfirmPassword").equals(map.get("userPassword"))) {
            return new StatusResult(404, "确认密码与密码不符");
        }
        //利用正则表达式来设置手机号格式
        String phone = "[1][0-9]{10}";
        //进行手机号格式的校验
        if (!map.get("mobile").matches(phone)) {
            return new StatusResult(404, "手机号格式错误");
        }
        //从session中取出验证码
        String reCode = (String) session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
        //进行验证码校验
        if (!map.get("code").equalsIgnoreCase(reCode)) {
            return new StatusResult(404, "验证码错误");
        }
        //实例化用户的对象
        User user1 = new User();
        //将从页面接收过来的值装进user对象中
        Jedis jedis = JedisPoolUtil.getJedis();
        long time = Long.parseLong(jedis.time().get(0)) * 1000;
        user1.setUserName(map.get("userName"));
        user1.setEmail(map.get("email"));
        user1.setUserPassword(map.get("userPassword"));
        user1.setUserPassword(MD5ShiroUtil.getMd5(user1));
        user1.setMobile(map.get("mobile"));
        user1.setAddtime(time);
        userMapper.insert(user1);
        return new StatusResult<Map>(200, "注册成功");
    }

    /**
     * 执行登录
     *
     * @param map
     * @return
     */
    @Override
    public StatusResult selectByUserName(Map<String, Object> map) {
        User user = userMapper.repeatByUserName((String) map.get("userName"));
        //从session中取出验证码
        String code = (String) session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
        //校验验证码
        if (!code.equalsIgnoreCase((String) map.get("code"))) {
            return new StatusResult<Map>(404, "验证码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token1 = new UsernamePasswordToken((String) map.get("userName"), (String) map.get("userPassword"));
        try {
            subject.login(token1);
            Map<String, String> payload = new HashMap<>();
            payload.put("id", user.getId().toString());
            payload.put("userName", user.getUserName());
            //生成token
            String token = JwtUtil.getToken(payload);
            map.put("token", token);
            //存入redis
            Jedis jedis = JedisPoolUtil.getJedis();
            jedis.setex("token", 60 * 60 * 24 * 7, token);
            //关闭资源
            jedis.close();
            return new StatusResult<Map>(200, "登录成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new StatusResult<Map>(404, "用户名或密码错误");
        }

    }

    /**
     * 退出登录的业务实现方法
     *
     * @return
     */
    @Override
    public StatusResult loginOut(String token) {
        Jedis jedis = JedisPoolUtil.getJedis();
        //用jedis删除redis里的token
        jedis.del("token");
        return new StatusResult<Map>(200, "注销成功");
    }

    /**
     * 查询用户表关联账户表的业务实现方法
     *
     * @return
     */
    @Override
    public StatusResult selectUserAndAccount() {
        //new个map
        Map<String, Object> map = new HashMap<>();
        //调用userMapper里的查询的方法
        List<User> userList = userMapper.selectUserAndAccount();
        if (userList == null) {
            return new StatusResult<Map>(404, "跳转个人中心失败");
        } else {
            //将查询出来的集合装进map里
            map.put("userList", userList);
            //返回页面
            return new StatusResult<Map>(200, "跳转个人中心成功", map);
        }

    }


    /**
     * 忘记密码根据用户名和手机号查询该用户的业务实现方法
     * @param map
     * @return
     */
    @Override
    public StatusResult selectByUserNameAndMobile(Map<String, Object> map) {
        //从session中取出验证码
        String code = (String) session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
        //验证码校验
        if (!code.equalsIgnoreCase((String) map.get("code"))){
            return new StatusResult<Map>(404, "验证码错误");
        }
        //根据页面传来的用户名和手机号查询mapper里的忘记密码的查询的方法
        User user = userMapper.selectByUserNameAndMobile((String) map.get("userName"), (String) map.get("mobile"));
        map.put("user",user);
        //进行user对象的校验
        if(user == null){
            return new StatusResult<Map>(404, "用户名或手机号错误");
        }else{
            //返回页面
            return new StatusResult<Map>(200, "验证成功",map);
        }
    }

    /**
     * 根据id查询用户的业务逻辑层
     * @param map
     * @return
     */
    @Override
    public StatusResult selectByPrimaryKey(Map<String, Object> map) {
        //调用mapper的根据id查询用户的方法
        User user = userMapper.selectByPrimaryKey(Long.valueOf(String.valueOf(map.get("id"))).longValue());
        if (user == null){
            return new StatusResult<Map>(404, "修改失败");
        }else{
            //将页面传来的密码set到根据页面传来的id查询出的user对象里
            user.setUserPassword((String) map.get("userPassword"));
            //调用修改用户的方法
            userMapper.updateByPrimaryKeySelective(user);
            return new StatusResult<Map>(200, "修改成功");
        }

    }
}


