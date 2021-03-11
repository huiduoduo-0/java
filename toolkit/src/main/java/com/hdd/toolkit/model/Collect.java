package com.hdd.toolkit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * tb_collect
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collect implements Serializable {
    /**
     * 收藏表的id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 添加时间
     */
    private Long addtime;

    /**
     * 商品颜色
     */
    private String color;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品简介
     */
    private String introduce;

    /**
     * 商品主图片路径
     */
    private String goodspictureurl;

    /**
     * 小商品图片路径
     */
    private String smallphotourl;

    /**
     * 商品名称
     */
    private String goodsname;

    /**
     * 规格值
     */
    private String specv;

    /**
     * 标题
     */
    private String title;

    private static final long serialVersionUID = 1L;


}