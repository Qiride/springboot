package com.webdemo.springbootwebdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 设置springboot的包
//@ComponentScan(basePackages = "com.webdemo.springbootwebdemo")

@SpringBootApplication
public class SpringBootWebDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebDemoApplication.class, args);
    }




}
