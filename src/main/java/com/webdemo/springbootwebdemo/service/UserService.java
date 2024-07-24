package com.webdemo.springbootwebdemo.service;

import com.webdemo.springbootwebdemo.pojo.User;

import java.util.List;

public interface UserService {
    //查找
    User findByUserName(String username);

    //添加
    void register(String username, String password);

    //更新
    void update(User user);
}
