package com.hdd.toolkit.webmagic;

import com.hdd.toolkit.model.Goods;
import com.hdd.toolkit.service.GoodsService;
import com.hdd.toolkit.utils.JedisPoolUtil;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;


@Component
public class JDGoodsPipeline implements Pipeline {

    @Resource
    private GoodsService goodsService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Goods goods = resultItems.get("goods");
        if (goods != null) {
            Jedis jedis = JedisPoolUtil.getJedis();
            long time = Long.parseLong(jedis.time().get(0)) * 1000;
            goods.setAddtime(time);
//            String token = jedis.get("token");
//            DecodedJWT decodedJWT = JwtUtil.getTokenInfo(token);
//            String id = decodedJWT.getClaim("id").asString();
//            goods.setUserId(Long.parseLong(id));
            //存入数据库
            goodsService.insert(goods);
        }
    }
    
}
