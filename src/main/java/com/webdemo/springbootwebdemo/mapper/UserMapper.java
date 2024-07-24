package com.webdemo.springbootwebdemo.mapper;

import com.webdemo.springbootwebdemo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    //查询用户
    @Select("select  * from user where username=#{username}")
    User findByUserName(String username);

    //添加用户
    @Insert("insert into user(username,password,create_time,update_time)"
            + "values(#{username},#{password},now(),now())")
    void add(String username, String password);

    //修改用户
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id = #{id}")
    void update(User user);



}
