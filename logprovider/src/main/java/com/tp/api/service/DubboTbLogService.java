package com.tp.api.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.tp.api.dbservice.DbTbLogService;
import com.tp.api.entity.TbLog;
import com.tp.api.mode.LoggerMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DubboTbLogService implements TbLogService {

    @Autowired
    DbTbLogService dbTbLogService;
    @Override
    public TbLog save(TbLog tbLog) {
        return dbTbLogService.save(tbLog);
    }

    @Override
    public List<TbLog> findAll(TbLog request) {
        return dbTbLogService.findAll(request);
    }

    @Override
    public boolean deleteAll(LoggerMessage request) {
        return dbTbLogService.deleteAll(request);
    }

    @Override
    public void ticket(TbLog request) {
        dbTbLogService.ticket(request);
    }

    @Override
    public TbLog findByUrl(TbLog request) {
        return dbTbLogService.findByUrl(request);
    }

    @Override
    public List<TbLog> groupBy(String name) {
        return dbTbLogService.groupBy(name);
    }

    @Override
    public boolean deleteById(long id) {
        return dbTbLogService.deleteById(id);
    }

    @Override
    public TbLog selectById(long id) {
        return dbTbLogService.selectById(id);
    }
}
