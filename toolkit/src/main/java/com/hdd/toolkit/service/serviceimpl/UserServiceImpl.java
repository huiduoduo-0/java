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

        return userMapper.selectByUser(user);
    }
}
