package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.ScheduleJob;
import com.hdd.toolkit.service.ScheduleJobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}