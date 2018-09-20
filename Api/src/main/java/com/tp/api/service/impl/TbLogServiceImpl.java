package com.tp.api.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tp.api.entity.TbLog;
import com.tp.api.mapper.TbLogMapper;
import com.tp.api.mode.LogRequest;
import com.tp.api.mode.LoggerMessage;
import com.tp.api.service.TbLogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tanping
 * @since 2018-09-19
 */
@Service
public class TbLogServiceImpl extends ServiceImpl<TbLogMapper, TbLog> implements TbLogService {



    @Override
    public TbLog save(TbLog tbLog) {
        TbLog result = baseMapper.findByUrl(tbLog.getUrl());

        if (result !=null) {
            if (tbLog.getApiname() != null) {
                tbLog.setApiname(result.getApiname());
            }
            tbLog.setId(result.getId());
            tbLog.setCreatedTime(result.getCreatedTime());
        }
        tbLog.setUpdateTime(new Date());
        insertOrUpdate(tbLog);
       return tbLog;
    }


    public List<TbLog> findAll(TbLog request){

        List<TbLog> logs =  baseMapper.findAll();
        logs.size();
        return logs;
    }

    @Override
    public boolean deleteAll(LoggerMessage request) {

        Condition condition =  Condition.create();
        if (request.getDomain() !=null) {
            condition.where("domain = {0}", request.getDomain());
        }
        baseMapper.delete(condition);

        return true;
    }
}
