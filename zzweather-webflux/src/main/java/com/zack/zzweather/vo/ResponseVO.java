package com.zack.zzweather.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;
}
