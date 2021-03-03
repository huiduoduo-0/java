package com.hdd.toolkit.dao;

import com.hdd.toolkit.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加订单的方法
     *
     * @param order
     * @return
     */
    int insert(Order order);

    /**
     * 动态添加
     *
     * @param order
     * @return
     */
    int insertSelective(Order order);

    /**
     * 根据主键查询的方法
     *
     * @param id
     * @return
     */
    Order selectByPrimaryKey(Long id);

    /**
     * 动态修改订单的方法
     *
     * @param order
     * @return
     */
    int updateByPrimaryKeySelective(Order order);

    /**
     * 根据主键修改
     *
     * @param order
     * @return
     */
    int updateByPrimaryKey(Order order);
}