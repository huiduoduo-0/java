package com.hdd.toolkit.service.impl;

import com.hdd.toolkit.dao.GoodsMapper;
import com.hdd.toolkit.model.Goods;
import com.hdd.toolkit.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        return null;
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
}