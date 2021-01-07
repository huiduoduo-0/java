package com.hdd.toolkit.service;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;

public interface UserService {
    StatusResult selectByUser(User user);

    StatusResult selectByUserName(User user);

    int insert(User user);

    //忘记密码
    StatusResult forget(User user);

    //前台 用户名查重
    StatusResult selectName(User user);
    //前台 电话查重
    StatusResult selectTel(User user);
    //前台 邮箱查重
    StatusResult selectEmail(User user);



}
