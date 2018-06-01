package com.tp.common.bean;

/**
 * 基础的 结果
 * @param <T>
 */
public class BaseResult<T> {

    public int code;
    public String message;
    public T data;
}
