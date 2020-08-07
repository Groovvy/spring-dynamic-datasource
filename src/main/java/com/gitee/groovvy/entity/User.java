package com.gitee.groovvy.entity;


/**
 * @author wanghuaan
 * @date 2020/8/6
 **/
public class User {

    private Integer userId;

    private String userName;

    private String loginName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}