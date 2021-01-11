package com.hdd.toolkit.utils;

import com.hdd.toolkit.model.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

/**
 * describe：加密，盐值为随机生成
 */
/*用于注册时候获取随机盐值进行加密*/
public class MD5ShiroUtil {
    public static String getMd5(User user) {
        //生成随机10位盐值
        String salt_value = SaltUtils.getSalt();
        //设置加盐方式
        Object salt = ByteSource.Util.bytes(salt_value);
        //给用户盐值以存到数据库
        user.setSalt(salt_value);
        //加密次数
        int hashIterations = 1024;
        //MD5hash加密方式
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(), salt, hashIterations);
        return md5Hash.toString();
    }

    /*获取数据库中存在的盐值进行加密*/
    public static String getMd5To(User user) {
        //获取用户盐值
        String salt_value = user.getSalt();
        //设置加密次数
        //设置加盐方式
        Object salt = ByteSource.Util.bytes(salt_value);
        int hashIterations = 1024;
        //MD5hash加密方式
        Md5Hash md5Hash = new Md5Hash(user.getUserPassword(), salt, hashIterations);
        return md5Hash.toString();
    }

}
