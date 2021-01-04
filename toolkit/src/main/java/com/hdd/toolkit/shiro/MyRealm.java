package com.hdd.toolkit.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;



public class MyRealm extends AuthorizingRealm {


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
        String userEmail = (String) token.getPrincipal();

      return null;
    }
}
