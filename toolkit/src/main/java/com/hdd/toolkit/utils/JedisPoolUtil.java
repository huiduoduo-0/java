package com.hdd.toolkit.utils;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolUtil {
    public static Jedis getJedis() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        //连接池中最大的活跃数
        poolConfig.setMaxTotal(100);
        //最大空闲数
        poolConfig.setMaxIdle(10);
        //最小空闲数
        poolConfig.setMinIdle(5);
        //超时
        poolConfig.setMaxWaitMillis(3000);
        //创建链接池对象
        JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379);
        //获取jedis
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }


}