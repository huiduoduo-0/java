package com.hdd.toolkit.service.impl;

import com.hdd.toolkit.dao.ScheduleJobMapper;
import com.hdd.toolkit.model.ScheduleJob;
import com.hdd.toolkit.service.ScheduleJobService;
import com.hdd.toolkit.webmagic.JDGoodsPipeline;
import com.hdd.toolkit.webmagic.JDProcessor;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import javax.annotation.Resource;

/**
 * (ScheduleJob)表服务实现类
 *
 * @author makejava
 * @since 2021-01-13 15:01:21
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
    @Resource
    private ScheduleJobMapper scheduleJobMapper;
    @Resource
    private JDGoodsPipeline jdGoodsPipeline;

    @Override
    public void start(ScheduleJob scheduleJob) {

        //将scheduleJob放入数据库
        scheduleJobMapper.insert(scheduleJob);
        long sid = scheduleJob.getId();
        //开启京东爬虫
        JDProcessor jDprocessor = new JDProcessor();
        jDprocessor.setSid(sid);
        String url = scheduleJob.getJobName();//爬取商品的地址
       //开始爬取数据
        Spider.create(jDprocessor)
                .addUrl(url)
                .thread(3)//线程数
                .addPipeline(jdGoodsPipeline)
                .start();
    }


}