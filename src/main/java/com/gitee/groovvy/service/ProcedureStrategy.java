package com.gitee.groovvy.service;


import com.gitee.groovvy.entity.User;

/**
 * @author wanghuaan
 * @date 2020/7/21
 **/
public interface ProcedureStrategy {
    /**
     * 调用SYS_USER存储过程
     */
    public User searchUserList(String type);

}
