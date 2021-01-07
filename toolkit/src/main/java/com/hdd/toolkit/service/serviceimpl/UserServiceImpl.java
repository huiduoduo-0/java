package com.hdd.toolkit.service.serviceimpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hdd.toolkit.dao.UserMapper;
import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import com.hdd.toolkit.utils.JwtUtil;
import com.hdd.toolkit.utils.Md5;
import com.hdd.toolkit.utils.RandomValidateCode;
import org.apache.commons.lang3.StringUtils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        String encode = Md5.encode(user);
        if (userDB != null) {
            if (encode.equals(userDB.getUserPassword())){
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
        User user3 = userMapper.selectTel(user);
        if (user3!=null){
            return new StatusResult(404,"该手机号已存在");
        }
        User user4 = userMapper.selectEmail(user);
        if (user4!=null){
            return new StatusResult(404,"该邮箱已存在");
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
        user2.setUserPassword(Md5.encode(user));
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

    /**
     * 根据用户id查询所有的用户方法的业务实现方法
     * @param id
     * @return
     */
    @Override
    public StatusResult<Map> SelectAll(Long id,String token) {
        //检验页面传递来的token跟redis里存的token一致
        if(token.equals(redisTemplate.opsForValue().get("token"))){
            //调用封装好的获取token方法
            DecodedJWT tokens=JwtUtil.getTokenInfo("token");
            //从token中取出登录时存入的id
            String tokenId=tokens.getClaims().get("id").asString();
            //根据token里的id调用查询所有的方法
            List<User> userList = userMapper.SelectAll(Long.valueOf(tokenId));
            //将查询出来的集合装进map中
            map.put("userList",userList);
            //将页面传来的token装进map中返回给页面
            map.put("token",token);
            //将map返回页面
            return new StatusResult<Map>(200, "跳转成功", map);
        }
        //页面传递来的token跟redis里存的token不一致，返回错误信息
        else {
            //返回给页面报错信息
            return new StatusResult<Map>(404,"登录超时,请重新登录");
        }

    }

    /**
     * 根据id查询出对象的业务实现方法
     * @param id
     * @return
     */
    @Override
    public User selectByPrimaryKey(Long id,String token) {
        //调用封装好的获取token方法
        DecodedJWT tokens=JwtUtil.getTokenInfo("token");
        //从token中取出登录时存入的id
        String tokenId=tokens.getClaims().get("id").asString();
        //返回根据id查询所有用户信息的方法
        return userMapper.selectByPrimaryKey(Long.valueOf(tokenId));
    }

    /**
     *执行修改个人信息的业务实现方法
     * @param user
     * @return
     */
    @Override
    public StatusResult<Map> doUpdate(User user, HttpServletRequest req, MultipartFile file) throws IOException {
        //调用封装好的获取token方法
        DecodedJWT tokens=JwtUtil.getTokenInfo("token");
        //从token中取出登录时存入的id
        String tokenId=tokens.getClaims().get("id").asString();
        //调用根据id查询用户对象的方法
        User user1 = userMapper.selectByPrimaryKey(Long.valueOf(tokenId));
        //调用查询用户名的方法
        User user2 = userMapper.selectByUserName(user);
        //校验用户名是否重复
        if (user2!=null){
            return new StatusResult(404,"该用户名已存在");
        }else {

        }
        //头像修改
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //如果文件不为空进行文件上传
        if (!StringUtils.isEmpty(fileName)){
        FileOutputStream fos = new FileOutputStream("D:\\uploadFiles/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.')));
        String path = "/upload/file/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.'));
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        //将文件的路径装进user1中
        user1.setImgPath(path);
        }else {
            return new StatusResult(404,"修改头像失败");
        }
        //执行修改用户信息的方法
        userMapper.updateByPrimaryKeySelective(user1);
        //返回修改成功信息
        return new StatusResult(200,"修改成功");
    }

    /**
     * 查找用户名重复的方法的业务实现方法
     * @param user
     * @return boolean
     */
    @Override
    public StatusResult<Map> SelectUserName(User user) {
        //调用封装好的获取token方法
        DecodedJWT tokens=JwtUtil.getTokenInfo("token");
        //从token中取出登录时存入的id
        String tokenId=tokens.getClaims().get("id").asString();
        //调用根据id查询用户对象的方法
        User user1 = userMapper.selectByPrimaryKey(Long.valueOf(tokenId));
        //调用查询用户名的方法
        User user2 = userMapper.selectByUserName(user);
        if (user2!=null){
            return  new StatusResult(404,"该用户名已存在");
        }else {
            //将页面传来的名字装进user1中
            user1.setUserName(user.getUserName());
            //执行修改用户信息的方法
            userMapper.updateByPrimaryKeySelective(user1);
            return new StatusResult(200,"该用户名可用");
        }

    }

    /**
     * 忘记密码执行逻辑
     * @param user
     * @return
     */
    @Override
    public StatusResult<Map> forget(User user) {
        User user1 = userMapper.selectByUserName(user);
        if (user1==null){
            return new StatusResult<Map>(404,"用户名不正确");
        }else
        if (!user1.getTel().equals(user.getTel())){
            return new StatusResult<Map>(404,"手机号不正确");
        }
        String code1 = (String) session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
        if (!user.getCode().equalsIgnoreCase(code1)){
            return new StatusResult<Map>(404,"验证码不正确");
        }
        String passwordpatter = "[0-9a-zA-Z]{6,20}";
        if (user.getUserPassword().matches(passwordpatter) == false){
            return new StatusResult<Map>(404,"密码格式不正确");
        }
        user1.setUserPassword(user.getUserPassword());
        //成功修改密码
        userMapper.updateByUsername(user1);
        return new StatusResult<Map>(200,"忘记密码执行成功");

    }

    //用户名查重
    @Override
    public boolean selectName(User user) {
        User user1 = userMapper.selectByUserName(user);
        if (user1!=null){
            return false;
        }
        return true;
    }
//电话查重
    @Override
    public boolean selectTel(User user) {
        User user1 = userMapper.selectTel(user);
        if (user1!=null){
            return false;
        }
        return true;
    }
//邮箱查重
    @Override
    public boolean selectEmail(User user) {
        User user1 = userMapper.selectEmail(user);
        if (user1!=null){
            return false;
        }
        return true;
    }


}
