package com.hdd.toolkit.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequestMapping("/code")
public class SafeCode {
    /**
     * 验证码
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/img",method = RequestMethod.GET)
    public void image(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getValidateCode(request, response);// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
