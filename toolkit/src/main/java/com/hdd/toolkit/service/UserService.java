package com.hdd.toolkit.service;

import com.hdd.toolkit.model.User;

public interface UserService {
    User selectByUser(User user);

    int insert(User user);
}
