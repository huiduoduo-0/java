package com.hdd.toolkit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * tb_user
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 验证码
     */
    private String code;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    private String realName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 用户头像
     */
    private String imgPath;

    /**
     * 邮箱
     */
    private String email;

   /**
    /**
     * 联系电话
     */
    private String tel;

    /**
     * 盐值（密码机密）
     */
    private String salt;

    /**
     * 区号
     */
    private String areaCode;

    /**
     * 商家简介
     */
    private String introduction;

    /**
     * 注册时间
     */
    private Long addtime;

    /**
     * 账户表的对象
     */
    private  Account account;

    private static final long serialVersionUID = 1L;

}