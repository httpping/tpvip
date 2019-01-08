package com.tp.api.dbservice;

import com.baomidou.mybatisplus.plugins.Page;
import com.tp.api.entity.TbString;
import com.baomidou.mybatisplus.service.IService;
import com.tp.api.mode.StringFilterRequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanping
 * @since 2018-10-11
 */
public interface DbTbStringService extends IService<TbString> {


    TbString saveOrUpdate(TbString tbString) throws IllegalAccessException;


    Page<TbString> select(StringFilterRequestParam param);


    List<TbString> groupBy(String name);

    boolean saveOrUpdateList(List<TbString> tbStrings) throws IllegalAccessException;

}
