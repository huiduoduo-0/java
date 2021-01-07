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
    boolean selectName(User user);
    //前台 电话查重
    boolean selectTel(User user);
    //前台 邮箱查重
    boolean selectEmail(User user);

}
