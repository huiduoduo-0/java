package com.hdd.toolkit.shiro;



import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.model.User;
import com.hdd.toolkit.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
   /* @Autowired
    private UserRoleMapper userRoleMapper;*/

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
      /*  获取账户信息（这里获取的是在认证最后SimpleAuthenticationInfo(principal,credentials,salt,realm)
        里面的第一个参数principal，你传的是什么就获取什么）
        User principal = (User) principals.getPrimaryPrincipal();
        Role role = userRoleMapper.queryUserRoleByUserId(principal);
        Set<String> roles = new HashSet<>();
        roles.add(role.getRoleName());
        System.out.println(roles);*/
//        return new SimpleAuthorizationInfo(roles);
        return null;
    }

    //认证(登录)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = (User) token.getPrincipal();

        //通过登录验证传过来的token结果获取唯一身份到数据库进行查询，若有，则进行认证；没有的话，抛出异常。
        StatusResult user1 = userService.selectByUserName(user);

        if (user != null) {

            String credentials = user.getUserPassword();

//            ByteSource salt = ByteSource.Util.bytes(user.getSalt());

            String realmName = this.getName();

//          SecurityUtils.getSubject().getSession().setAttribute("user",user);


//            return new SimpleAuthenticationInfo(user1, credentials, salt, realmName);

        } else {
            throw new UnknownAccountException();
        }
        return null;
    }
}
