package com.webdemo.springbootwebdemo;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {
    //生成token
    @Test
    public void testGen(){

        Map<String,Object> claims = new HashMap<>();
        claims.put("id","id");
        claims.put("username","name");

        String token = JWT.create()
                .withClaim("user",claims)//添加负荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*24))//添加过期时间
                .sign(Algorithm.HMAC256("drd"));

//        System.out.println(token);

    }

    //对比token
    @Test
    public void testParse(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiaWQiLCJ1c2VybmFtZSI6Im5hbWUifSwiZXhwIjoxNzIwMDgwNzc3fQ.-Ftob15349Xh1i9RJuV9bq_GDLfRXaPH0DBZOY67oGQ";


        JWTVerifier drd = JWT.require(Algorithm.HMAC256("drd")).build();

        drd.verify(token);

        DecodedJWT decodedJWT = drd.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

    }
    
}
