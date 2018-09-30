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
public class BaseResponse<T>  {


    public static  final int SUCCESS = 200;

    public int code;
    public String message;
    public T data;


    public  static <T> BaseResponse created(int code , String message, T data){
        BaseResponse result = new BaseResponse();
        result.code = code;
        result.message = message;
        result.data = data;
        return  result;
    }


    public  static <T> BaseResponse created(int code , String message){
        BaseResponse result = new BaseResponse();
        result.code = code;
        result.message = message;
        return  result;
    }
}