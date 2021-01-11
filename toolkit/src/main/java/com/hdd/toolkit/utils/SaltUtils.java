package com.hdd.toolkit.utils;

import java.util.Random;

/**
 * @Author yanjie
 * @Date 2020/9/9 19:45
 */
public class SaltUtils {
    /**
     * 生成随机字符的salt的静态方法
     *
     * @param
     * @return
     */
    public static String getSalt() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    /**
     * 生成随机字符的静态方法
     *
     * @param
     * @return
     */
    public static String getsjs() {
        char[] chars = "01234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getsjs());
    }
}
