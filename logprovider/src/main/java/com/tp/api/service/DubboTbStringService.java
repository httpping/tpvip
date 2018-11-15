package com.tp.api.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.tp.api.dbservice.DbTbStringService;
import com.tp.api.entity.TbString;
import com.tp.api.mode.StringFilterRequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class DubboTbStringService implements TbStringService{

    @Autowired
    DbTbStringService dbTbStringService;

    @Override
    public TbString saveOrUpdate(TbString tbString) {
        return dbTbStringService.saveOrUpdate(tbString);
    }

    @Override
    public Page<TbString> select(StringFilterRequestParam param) {
        return dbTbStringService.select(param);
    }

    @Override
    public List<TbString> groupBy(String name) {
        return dbTbStringService.groupBy(name);
    }

    @Override
    public boolean saveOrUpdateList(List<TbString> tbStrings) {
        return dbTbStringService.saveOrUpdateList(tbStrings);
    }
}
