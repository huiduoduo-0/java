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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getAddtime() {
        return addtime;
    }

    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    private static final long serialVersionUID = 1L;

}