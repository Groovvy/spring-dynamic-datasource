package com.gitee.groovvy.controller;

import com.gitee.groovvy.config.DataSourceContext;
import com.gitee.groovvy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @author wanghuaan
 * @date 2020/8/6
 **/
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
	DataSourceContext dataSourceContext;

	/**
	 * 切换数据源示例
	 * 
	 * @return
	 */
	@GetMapping("/get/{param}")
	public Object get(@PathVariable("param") String param) {
		Map<String, Object> map = new HashMap<>();
		User user = dataSourceContext.searchUser(param);
		map.put("user", user);
		return map;
	}
	
}
