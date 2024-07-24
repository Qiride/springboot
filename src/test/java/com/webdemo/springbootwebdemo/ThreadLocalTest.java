package com.webdemo.springbootwebdemo;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {


    @Test
    public void testThreadLocalSetAndGet(){
        //线程可开启多个独立的请求
        ThreadLocal tl = new ThreadLocal();

        new Thread(()->{
            tl.set("消炎");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"蓝色").start();

        new Thread(()->{
            tl.set("腰身");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"绿色").start();

    }


}
