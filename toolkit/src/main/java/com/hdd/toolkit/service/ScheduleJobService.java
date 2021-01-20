package com.hdd.toolkit.service;

import com.hdd.toolkit.model.ScheduleJob;
import java.util.Map;

/**
 * (ScheduleJob)表服务接口
 *
 * @author makejava
 * @since 2021-01-13 15:01:21
 */
public interface ScheduleJobService {

    /**
     * 开启爬虫
     *
     * @param scheduleJob
     */
    void start(ScheduleJob scheduleJob);

    public Map<String, Object> getMessage();
}