package com.hdd.toolkit.dao;

import com.hdd.toolkit.model.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CollectMapper {
    /**
     * 根据主键删除收藏
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入收藏的语句
     * @param collect
     * @return
     */
    int insert(Collect collect);

    /**
     * 动态插入收藏的语句
     * @param collect
     * @return
     */
    int insertSelective(Collect collect);

    /**
     * 根据主键查询语句
     * @param id
     * @return
     */
    Collect selectByPrimaryKey(Long id);

    /**
     * 动态修改收藏表
     * @param collect
     * @return
     */
    int updateByPrimaryKeySelective(Collect collect);

    /**
     * 修改收藏语句
     * @param collect
     * @return
     */
    int updateByPrimaryKey(Collect collect);

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
