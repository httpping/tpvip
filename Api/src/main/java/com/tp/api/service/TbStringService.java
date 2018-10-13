package com.tp.api.service;

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
public interface TbStringService extends IService<TbString> {


    TbString saveOrUpdate(TbString tbString);


    List<TbString> select(StringFilterRequestParam param);

    boolean saveOrUpdateList(List<TbString> tbStrings);

}
