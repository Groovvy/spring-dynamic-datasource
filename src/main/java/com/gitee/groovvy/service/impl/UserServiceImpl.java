package com.gitee.groovvy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitee.groovvy.entity.User;
import com.gitee.groovvy.mapper.UserMapper;
import com.gitee.groovvy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired private UserMapper userMapper;
	
	@Override
	public User get(String targetSource) {
		return userMapper.selectByPrimaryKey(1);
	}

}
