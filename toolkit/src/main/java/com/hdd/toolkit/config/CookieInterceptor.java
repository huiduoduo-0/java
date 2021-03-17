package com.hdd.toolkit.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hdd.toolkit.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 拦截器的配置
 */
public class CookieInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("===============开始拦截==============");
        //从前台传来的表头中提出token
        String tokenHeader = request.getHeader("Authorization");
        //如果页面传来的表头没有token,就重新登录
        if (tokenHeader == null || ("").equals(tokenHeader)) {
            response.sendError(401, "表头没有携带token,重新登录");
            return false; //拦截
        }
        //token的失效拦截
        DecodedJWT jwt = JwtUtil.getTokenInfo("tokenHeader");
        //获取token失效时间
        Date tokenInvalidTime = jwt.getExpiresAt();
        //将token失效的时间转换成毫秒
        long InvalidTime = tokenInvalidTime.getTime();
        //获取系统当前时间
        Date date = new Date();
        //将系统当前时间转成毫秒
        long nowTime = date.getTime();
        //token过期时间和当前系统时间比较,token失效,重新登录
        if (InvalidTime < nowTime) {
            response.sendError(401, "token失效,重新登录");
            return false; //拦截
        }
        return true;
    }

}
