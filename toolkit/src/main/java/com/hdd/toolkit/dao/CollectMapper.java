package com.hdd.toolkit.dao;

import com.hdd.toolkit.model.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    /**
     * 根据商品id查询我的收藏是否存在此商品
     *
     * @param userId
     * @param goodsId
     * @return
     */
    Collect findCollectByGoodsId(@Param(value = "userId") String userId, @Param(value = "goodsId") String goodsId);

    /**
     * 根据用户id查询该用户的所有的收藏的商品信息
     *
     * @param userId
     * @return
     */
    List<Collect> selectAllCollectByUserId(@Param(value = "userId") String userId);
}