package com.hdd.toolkit.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {

    private static final String signature = "jskjfian7#$^&&HB*^%#!FT";

    /*
     * 生成token header.payload.signature
     * */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);//七天有效时间
        //创建JWT builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(signature));
        return token;
    }

    /*
     * 验证token
     * */
    public static void verifyToken(String token) {
        JWT.require(Algorithm.HMAC256(signature)).build().verify(token);
    }

    /*
     * 获取token信息
     * */
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(signature)).build().verify(token);
        return verify;
    }
}
