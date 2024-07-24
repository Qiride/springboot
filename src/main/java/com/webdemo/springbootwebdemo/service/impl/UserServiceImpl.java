package com.webdemo.springbootwebdemo.service.impl;

import com.webdemo.springbootwebdemo.mapper.UserMapper;
import com.webdemo.springbootwebdemo.pojo.User;
import com.webdemo.springbootwebdemo.service.UserService;
import com.webdemo.springbootwebdemo.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User s = userMapper.findByUserName(username);
        return s;
    }

    @Override
    public void register(String username,String password) {
        //加密
        String s = Md5Util.getMD5String(password);
        //添加
        userMapper.add(username,s);

    }

    @Override
    public void update(User user) {

        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);

    }


}
