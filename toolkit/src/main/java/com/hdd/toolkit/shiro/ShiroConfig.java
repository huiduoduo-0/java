package com.hdd.toolkit.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        HashMap<String, String> map = new HashMap<>();
        /*
        authc:代表必须要认证过后才能访问
        anon:代表不需要认证就可以访问
        */
        map.put("/user/register","anon");
        map.put("/user/login","anon");
        map.put("/user/test","anon");
        map.put("/**","anon");

        factoryBean.setFilterChainDefinitionMap(map);
        factoryBean.setLoginUrl("/user/login");
        return factoryBean;
    }


    //根据name进行注入，ioc容器中会管理多个Realm,如果使用类型注入，可能导致注入的不是自己想要的Realm
    @Bean
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("myRealm") MyRealm myRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager. setRealm(myRealm);

        return securityManager;
    }


    @Bean(name = "myRealm")
    public MyRealm getMyRealm(){
        MyRealm myRealm = new MyRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(1024);
        matcher.setHashAlgorithmName("md5");
        myRealm.setCredentialsMatcher(matcher);
        return myRealm;

    }


}
