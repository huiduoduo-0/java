package com.hdd.toolkit.service;

import com.hdd.toolkit.model.StatusResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {

    /**
     * 查询用户名是否重复的方法的业务逻辑层
     *
     * @param userName
     * @return
     */
    StatusResult repeatByUserName(@Param(value = "userName") String userName, @Param(value = "id") String id);

    /**
     * 注册用户的方法的业务逻辑层
     *
     * @return
     */
    StatusResult doRegister(Map<String, String> map);

    StatusResult selectByUserName(Map<String, Object> map);


}
