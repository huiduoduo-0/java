package com.hdd.toolkit.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * tb_order
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品数量
     */
    private Long count;

    /**
     * 订单总价
     */
    private BigDecimal orderPrice;

    /**
     * 添加订单时间
     */
    private Long orderAddtime;

    /**
     * 订单号
     */
    private Long orderNumber;

    /**
     * 订单状态
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}