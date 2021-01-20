package com.hdd.toolkit.model;

import java.io.Serializable;
import lombok.Data;

/**
 * tb_goods
 * @author 
 */
@Data
public class Goods implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 商品标题
     */
    private String tittle;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 商品图片
     */
    private String imgUrl;

    /**
     * 商品路径
     */
    private String url;

    /**
     * 任务id
     */
    private Long sid;

    /**
     * 商品介绍
     */
    private String introduce;

    /**
     * 规格与规格值
     */
    private String spec;

    /**
     * 关联的用户id
     */
    private Long userId;

    /**
     * 添加时间
     */
    private Long addtime;

    private static final long serialVersionUID = 1L;
}