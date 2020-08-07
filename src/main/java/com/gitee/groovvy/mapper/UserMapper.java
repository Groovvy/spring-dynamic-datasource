package com.gitee.groovvy.mapper;

import com.gitee.groovvy.entity.User;
import com.gitee.groovvy.entity.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanghuaan
 * @date 2020/8/6
 **/
@Mapper
public interface UserMapper {

    long countByExample(UserExample example);


    int deleteByExample(UserExample example);


    int deleteByPrimaryKey(Integer id);


    int insert(User record);


    int insertSelective(User record);


    List<User> selectByExample(UserExample example);


    User selectByPrimaryKey(Integer id);


    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);


    int updateByExample(@Param("record") User record, @Param("example") UserExample example);


    int updateByPrimaryKeySelective(User record);


    int updateByPrimaryKey(User record);
}