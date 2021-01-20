package com.hdd.toolkit.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hdd.toolkit.dao.ScheduleJobMapper;
import com.hdd.toolkit.model.ScheduleJob;
import com.hdd.toolkit.service.ScheduleJobService;
import com.hdd.toolkit.utils.JedisPoolUtil;
import com.hdd.toolkit.webmagic.JDGoodsPipeline;
import com.hdd.toolkit.webmagic.JDProcessor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<String> JDUrls = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            String url = scheduleJob.getJobName();//爬取商品的地址
            JDUrls.add(url);
        }
       //开始爬取数据
        Spider.create(jDprocessor)
                .addUrl(JDUrls.toArray(new String[3]))
                .thread(1)//线程数
                .addPipeline(jdGoodsPipeline)
                .start();
    }

    @Override
    public Map<String, Object> getMessage(){
        Map<String, Object> map = new HashMap<>();
        Jedis jedis = JedisPoolUtil.getJedis();
        String goods = jedis.get("goods");
        JSONArray jsonArray = JSON.parseArray(goods);
        map.put("goods", jsonArray.toArray());
        return map;
    }


}