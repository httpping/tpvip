package com.tp.common.bean;

import com.baomidou.mybatisplus.plugins.Page;

import java.io.Serializable;
import java.util.List;

/**
 * page 基础的 结果
 * @param
 */
public class BasePageResult<ENTITY> implements Serializable{

    public int total;
    public int size;
    public int pages;
    public int current;
    public int code;
    public String message;
    public List<ENTITY>  data;



    public  BasePageResult converter(Page<ENTITY> page){
        total = page.getTotal();
        size = page.getSize();
        pages = page.getPages();
        current = page.getCurrent();
        data = page.getRecords();

        return this;
    }

    public static BasePageResult created(Page page){
        BasePageResult result = new BasePageResult();
        result.converter(page);
        return result;
    }

    public BasePageResult isSuccess(){
        return  this;
    }
}
