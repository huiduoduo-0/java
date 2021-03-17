package com.hdd.toolkit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorCofig extends WebMvcConfigurationSupport {

    @Bean
    public CookieInterceptor cookieInterceptor(){
        //这里对拦截器进行bean处理
        return new CookieInterceptor();
    }

    /**
     * cookieInterceptor()拦截器注册
     * addPathPatterns拦截的请求
     * excludePathPatterns放行的请求
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
                                                    //拦截的请求             //放行的请求
        registry.addInterceptor(cookieInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/**","/code/**");
        super.addInterceptors(registry);
    }

    //跨域问题解决
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/action/**");
    }


}
