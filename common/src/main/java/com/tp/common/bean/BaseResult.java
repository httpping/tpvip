package com.tp.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 基础的 结果
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> {

    public int code;
    public String message;
    public T data;

}
