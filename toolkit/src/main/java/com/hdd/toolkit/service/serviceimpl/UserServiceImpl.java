package com.hdd.toolkit.service.serviceimpl;

import com.hdd.toolkit.dao.UserMapper;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User selectByUser(User user) {
        //根据用户名和密码查询
        User userDB = userMapper.selectByUser(user);
        if (userDB != null){
            return  userDB;
        }
    throw new RuntimeException("认证失败");
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }
}
