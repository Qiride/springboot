package com.webdemo.springbootwebdemo.interceptors;

import com.webdemo.springbootwebdemo.pojo.Result;
import com.webdemo.springbootwebdemo.utils.JwtUtil;
import com.webdemo.springbootwebdemo.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
//拦截器
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");
        try {
            Map<String, Object> stringObjectMap = JwtUtil.parseToken(authorization);
            //储存ThreadLocal
            ThreadLocalUtil.set(stringObjectMap);

            return true;
        }catch (Exception e){

            response.setStatus(401);
            //不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        //防止内存泄露
        //清空ThreadLocal中的数据
        ThreadLocalUtil.remove();

    }
}
