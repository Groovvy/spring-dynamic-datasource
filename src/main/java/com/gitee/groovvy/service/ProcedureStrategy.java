package com.gitee.groovvy.service;


import com.gitee.groovvy.entity.User;

/**
 * @author wanghuaan
 * @date 2020/7/21
 **/
public interface ProcedureStrategy {
    /**
     * 调用不同数据库的存储过程
     */
    public User searchUser(String type);

}
