package com.hdd.toolkit.service;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;

public interface UserService {
    StatusResult selectByUser(User user);

    StatusResult selectByUserName(User user);

    int insert(User user);


}
