package com.hdd.toolkit.utils;


import com.hdd.toolkit.model.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * describe：这是shiro的加密方式，盐值为随机生成的十位数
 */
    /*用于注册时候获取随机盐值进行加密*/
public class MD5ShiroUtil {
    public static String getMd5(User user) {
        //加密方式，用SimpleHash类获取加密密码时需要用到，用Md5Hash获取则不用指定
        String salt_value = SaltUtils.getSalt();
        user.setSalt(salt_value);
        //设置加盐方式
        Object salt = ByteSource.Util.bytes(salt_value);
        //加密多少次
        int hashIterations = 1024;
        //MD5hash加密方式
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(),salt,hashIterations);
        //SimpleHash加密方式()：与MD5hash不同的是，第一个参数要指定一MD5方式加密
//        SimpleHash MD5 = new SimpleHash(algorithmName, user.getPassword(), salt, hashIterations);
//        return MD5.toString();
        return md5Hash.toString();
    }
    /*获取数据库中存在的盐值进行加密*/
    public static String getMd5To(User user) {
        //加密方式，用SimpleHash类获取加密密码时需要用到，用Md5Hash获取则不用指定
//      String algorithmName = "MD5";
        String salt_value = user.getSalt();
        user.setSalt(salt_value);
        //设置加盐方式(一般用用户名来加盐)
        Object salt = ByteSource.Util.bytes(salt_value);
        //加密多少次
        int hashIterations = 1024;
        //MD5hash加密方式
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(),salt,hashIterations);
        //SimpleHash加密方式()：与MD5hash不同的是，第一个参数要指定一MD5方式加密
//        SimpleHash MD5 = new SimpleHash(algorithmName, user.getPassword(), salt, hashIterations);
//        return MD5.toString();
        return md5Hash.toString();
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUserPassword("123456");
        System.out.println(MD5ShiroUtil.getMd5(user));
    }
}
