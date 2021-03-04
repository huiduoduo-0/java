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
	}}
