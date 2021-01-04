package com.hdd.toolkit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 错误状态
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResult <T>{
    private Integer code;
    private String message;
    private T   date;
    public StatusResult(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}