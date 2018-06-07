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
public class BaseResult<T>  {

    public int code;
    public String message;
    public T data;


    public  static <T> BaseResult created(int code ,String message,T data){
        BaseResult result = new BaseResult();
        result.code = code;
        result.message = message;
        result.data = data;
        return  result;
    }


    public  static <T> BaseResult created(int code ,String message){
        BaseResult result = new BaseResult();
        result.code = code;
        result.message = message;
        return  result;
    }
}
