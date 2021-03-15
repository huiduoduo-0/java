package com.hdd.toolkit.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hdd.toolkit.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Component
public class WebInterceptor implements HandlerInterceptor {


    /**
     * 拦截所有请求同时放行login和验证码请求和拦截失效的token
     *
     * @param request reponse跨域请求处理
     * @param handler 跨域请求处理
     * @return 返回 是否拦截
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //从前台传来的表头中提出token
        String tokenHeader = request.getHeader("Authorization");
        //如果页面传来的表头没有token,就重新登录
        if (tokenHeader == null || ("").equals(tokenHeader)) {
            response.sendError(401,"表头没有携带token,重新登录");
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
			response.sendError(401,"token失效,重新登录");
            return false; //拦截
        }
        this.setResponseCrossOrigin(request, response);
        return true;

    }

    /**
     * 设定响应对象允许跨域的必要条件
     *
     * @param request  当前请求
     * @param response 当前响应
     * @return 被设定允许跨域的响应
     */
    public HttpServletResponse setResponseCrossOrigin(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Access-Control-Expose-Headers", "TOKEN");
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        return response;
    }
}
