package com.hdd.toolkit.controller;

import com.hdd.toolkit.model.StatusResult;
import com.hdd.toolkit.service.CollectService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Resource
    CollectService collectService;

    /**
     * 查询我的收藏是否存在此商品
     *
     * @return
     */
    @GetMapping(value = "/repeatGoods")
    public StatusResult repeatGoods(@RequestBody Map<String, Object> map) {
        //调用收藏的service的,根据商品id查询收藏表是否存在此商品
        return collectService.findCollectByGoodsId(map);
    }

    /**
     * 根据用户id查询该用户的所有的收藏的商品信息的方法
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/selectAllCollect")
    public StatusResult selectAllCollect(@RequestBody Map<String, Object> map) {
        //调用查询该用户的所有收藏商品的方法
        return collectService.selectAllCollectByUserId(map);
    }

    /**
     * 删除我的收藏里商品信息
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/deleteCollect")
    public StatusResult deleteCollect(@RequestBody Map<String, Object> map) {
        //调用删除的方法
        return collectService.deleteCollect(map);
    }
}
