package com.gitee.groovvy.service.impl;


import com.gitee.groovvy.entity.User;
import com.gitee.groovvy.mapper.UserMapper;
import com.gitee.groovvy.service.ProcedureStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanghuaan
 * @date 2020/7/21
 **/
@Service("db0")
public class Db2Strategy implements ProcedureStrategy {


    @Autowired
    private UserMapper userMapper;


    @Override
    public User searchUser(String type) {
        System.out.println("假装调用db2的存储过程");
        User user = userMapper.selectByPrimaryKey(1);

        return user;
    }
}
