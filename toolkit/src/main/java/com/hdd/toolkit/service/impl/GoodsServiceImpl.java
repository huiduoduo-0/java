package com.hdd.toolkit.service.impl;

import com.hdd.toolkit.dao.GoodsMapper;
import com.hdd.toolkit.model.Goods;
import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (TbGoods)表服务实现类
 *
 * @author makejava
 * @since 2021-01-12 17:02:07
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;


    @Override
    public Goods queryById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Goods> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public Goods insert(Goods goods) {
        goodsMapper.insert(goods);
        return goods;
    }

    @Override
    public Goods update(Goods goods) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public StatusResult showGoods() {
        List<Goods> goodsList = goodsMapper.queryAllGoods();
        Map<String,Object> goodsMap = new HashMap<>();
        goodsMap.put("goodsList",goodsList);
        return new StatusResult(200,"取到数据",goodsMap);
    }

}