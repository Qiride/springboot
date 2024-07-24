package com.webdemo.springbootwebdemo.controller;


import com.auth0.jwt.JWT;
import com.webdemo.springbootwebdemo.pojo.Result;
import com.webdemo.springbootwebdemo.pojo.User;
import com.webdemo.springbootwebdemo.service.UserService;
import com.webdemo.springbootwebdemo.utils.JwtUtil;
import com.webdemo.springbootwebdemo.utils.Md5Util;
import com.webdemo.springbootwebdemo.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserContriller {

    @Autowired
    private UserService userService;
    //注册接口
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$") String password) {
        //查
        User u = userService.findByUserName(username);
        if(u==null){
            //没有
            userService.register(username,password);
            return Result.success();
        }else {
            //有
            return Result.error("已被占用");
        }

    }

    //登录接口
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        User LoginUser = userService.findByUserName(username);

        if(LoginUser==null) {
            return Result.error("用户名错误");
        }

        if(Md5Util.getMD5String(password).equals(LoginUser.getPassword())){

            Map<String ,Object> claims = new HashMap<>();
            claims.put("id",LoginUser.getId());
            claims.put("username",LoginUser.getUsername());
            String s = JwtUtil.genToken(claims);

            return Result.success(s);

        }

        return Result.error("密码错误");
    }

    //获取用户个人信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(){

        //打开ThreadLocal
        Map<String,Object> map = ThreadLocalUtil.get();
        String s = (String) map.get("username");
        User user = userService.findByUserName(s);
        return Result.success(user);

    }

    //修改个人信息
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }




}
