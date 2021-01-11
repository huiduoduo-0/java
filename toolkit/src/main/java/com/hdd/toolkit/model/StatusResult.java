package com.hdd.toolkit.model;

import lombok.Data;

/**
 * 错误状态
 *
 * @param <T>
 */
@Data
public class StatusResult<T> {
    private Integer code;
    private String message;
    private T date;

    public StatusResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public StatusResult(Integer code, String message, T date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }
}