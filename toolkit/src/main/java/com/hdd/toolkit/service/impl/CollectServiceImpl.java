package com.hdd.toolkit.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hdd.toolkit.dao.CollectMapper;
import com.hdd.toolkit.model.Collect;
import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.service.CollectService;
import com.hdd.toolkit.utils.JedisPoolUtil;
import com.hdd.toolkit.utils.JwtUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    CollectMapper collectMapper;

    /**
     * 查询我的收藏里是否存在此商品的业务实现方法并添加我的收藏的方法
     *
     * @param map
     * @return
     */
    @Override
    public StatusResult findCollectByGoodsId(Map<String, Object> map) {
        //获取token的用户id
        DecodedJWT jwt = JwtUtil.getTokenInfo((String) map.get("token"));
        String userId = jwt.getClaim("id").asString();
        //调用查询我的收藏是否存在此商品的方法
        Collect collect = collectMapper.findCollectByGoodsId(userId, (String) map.get("goodsId"));
        //进行校验
        if (collect != null) {
            return new StatusResult<Map>(404, "我的收藏已存在此商品");
        } else {
            //实例化collect用collect1接
            Collect collect1 = new Collect();
            //将页面传来的商品信息set进collect1中
            collect1.setUserId((long) map.get("userId"));
            collect1.setGoodsId((long) map.get("goodsId"));
            collect1.setColor((String) map.get("color"));
            collect1.setGoodsname((String) map.get("goodsName"));
            collect1.setIntroduce((String) map.get("introduce"));
            collect1.setGoodspictureurl((String) map.get("goodsPictureUrl"));
            collect1.setSmallphotourl((String) map.get("smallPhotoUrl"));
            collect1.setSpecv((String) map.get("specv"));
            //从jedis获取当前时间
            Jedis jedis = JedisPoolUtil.getJedis();
            long time = Long.parseLong(jedis.time().get(0)) * 1000;
            //将从jedis里获取的时间set进collect1中
            collect1.setAddtime(time);
            collect1.setPrice((BigDecimal) map.get("price"));
            //将从token中取出的用户id存进collect1中
            collect1.setUserId(Long.valueOf(String.valueOf(userId)).longValue());
            //调用添加我的收藏的方法
            int count = collectMapper.insert(collect1);
            //进行添加校验
            if (count == 0) {
                return new StatusResult<Map>( 404, "添加失败");
            } else {
                return new StatusResult<Map>(200, "可以加入我的收藏");
            }
        }
    }

    /**
     * 根据用户id查询该用户的所有的收藏的商品信息的业务实现方法
     *
     * @param map
     * 、
     * 
     * @return
     */
    @Override
    public StatusResult selectAllCollectByUserId(Map<String, Object> map) {
        //获取token的用户id
        DecodedJWT jwt = JwtUtil.getTokenInfo((String) map.get("token"));
        String userId = jwt.getClaim("id").asString();
        //调用根据用户id查询我的收藏方法
        List<Collect> listCollect = collectMapper.selectAllCollectByUserId(userId);
        //将集合存进map中
        map.put("listCollect", listCollect);
        return new StatusResult<Map>(200, "跳转我的收藏成功", map);
    }

    /**
     * 删除我的收藏里商品的方法的业务实现方法
     *
     * @param map
     * @return
     */
    @Override
    public StatusResult deleteCollect(Map<String, Object> map) {
        //调用根据商品id删除我的收藏里商品的方法
        int count = collectMapper.deleteByPrimaryKey((long) map.get("goodsId"));
        //进行删除成功校验
        if (count == 0) {
            return new StatusResult<Map>(200, "删除成功");
        } else {
            return new StatusResult<Map>(404, "删除失败");
        }
    }



}
