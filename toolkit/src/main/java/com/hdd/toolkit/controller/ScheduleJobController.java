package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.ScheduleJob;
import com.hdd.toolkit.service.ScheduleJobService;
import com.hdd.toolkit.utils.RandomValidateCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * (ScheduleJob)表控制层
 *
 * @author makejava
 * @since 2021-01-13 15:01:21
 */
@RestController
@RequestMapping("scheduleJob")
public class ScheduleJobController {
    /**
     * 服务对象
     */
    @Resource
    private ScheduleJobService scheduleJobService;


    @RequestMapping("startSearch")
    public String startSearch(ScheduleJob scheduleJob) {

        scheduleJobService.start(scheduleJob);

        return "成功";
    }

    @RestController
    @RequestMapping("/code")
    public static class
    SafeCodeController {
        /**
         * 验证码
         *
         * @param request
         * @param response
         */
        @RequestMapping(value = "/img", method = RequestMethod.GET)
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
}