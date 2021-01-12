package com.hdd.toolkit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * tb_account
 *
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 余额(点数)
     */
    private Long surplus;

    /**
     * 总积分
     */
    private Integer integral;

    /**
     * 总消费金额
     */
    private BigDecimal totalConsume;

    private static final long serialVersionUID = 1L;

}