package com.hdd.toolkit.service;

import com.hdd.toolkit.model.StatusResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface CollectService {
    /**
     * 查询我的收藏里是否存在此商品的业务逻辑层
     *
     * @param map
     * @return
     */
    StatusResult findCollectByGoodsId(Map<String, Object> map, HttpServletRequest request);

    /**
     * 根据用户id查询该用户的所有的收藏的商品信息的业务逻辑层
     *
     * @param map
     * @return
     */
    StatusResult selectAllCollectByUserId(Map<String, Object> map, HttpServletRequest request);

    /**
     * 删除我的收藏里商品的方法的业务逻辑层
     *
     * @param map
     * @return
     */
    StatusResult deleteCollect(Map<String, Object> map);



}
