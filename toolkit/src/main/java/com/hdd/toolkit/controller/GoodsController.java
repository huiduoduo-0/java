package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.Goods;
import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.service.GoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    GoodsService goodsService;

    @RequestMapping("/search")
    public StatusResult showGoods(){

        return goodsService.showGoods();
    }
}
