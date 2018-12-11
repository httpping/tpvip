package com.tp.api.dbservice.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tp.api.dbservice.DbTbAnalysisLogService;
import com.tp.api.dbservice.DbTbLogService;
import com.tp.api.entity.TbAnalysisLog;
import com.tp.api.entity.TbLog;
import com.tp.api.mapper.TbLogMapper;
import com.tp.api.mode.LoggerMessage;
import com.tp.api.service.TbAnalysisLogService;
import com.tp.api.utils.DateUtis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
public class TbLogServiceImpl extends ServiceImpl<TbLogMapper, TbLog> implements DbTbLogService {


    @Autowired
    DbTbAnalysisLogService tbAnalysisLogService;


    @Override
    public TbLog save(TbLog tbLog) {
        TbLog result = baseMapper.findByUrl(tbLog.getUrl());

        if (result !=null) {
            if (tbLog.getApiname() != null) {
                tbLog.setApiname(result.getApiname());
            }
            //锁定状态不能被修改
            if (result.getTicket() == 1){
                return result;
            }
            tbLog.setId(result.getId());
            tbLog.setCreatedTime(result.getCreatedTime());

            //和上一次的结果取 平均值
            if (result.getFeeTime()>0){
                int avg = (tbLog.getFeeTime() + result.getFeeTime())/2;
                tbLog.setFeeTime(avg);
            }
            tbLog.setVisitsNumber(result.getVisitsNumber() +1 );
        }
        insertOrUpdate(tbLog);

        TbAnalysisLog tbAnalysisLog = new TbAnalysisLog();
        tbAnalysisLog.setDomain(tbLog.getDomain());
        tbAnalysisLog.setUpdateDate(new Date());
        tbAnalysisLog.setMonthDay(DateUtis.formatMMDD());
        tbAnalysisLog.setMonth(DateUtis.getCurrentMonth()+"");
        tbAnalysisLog.setDay(DateUtis.getCurrentDay()+"");

        tbAnalysisLogService.saveAndUpdate(tbAnalysisLog);

       return tbLog;
    }


    public List<TbLog> findAll(TbLog request){

//        List<TbLog> logs =  baseMapper.findAll();

        Condition condition =  Condition.create();
        if (!StringUtils.isEmpty(request.getDomain())) {
            condition.where("domain = {0}", request.getDomain());
        }
        if (!StringUtils.isEmpty(request.getUrl())){
            condition.and("url like {0}", "%" +request.getUrl() +"%");
        }


        List desc = new ArrayList();
        desc.add("update_time");
        condition.orderDesc(desc);
        List<TbLog> logs = baseMapper.selectList(condition);
        return logs;
    }

    @Override
    public boolean deleteAll(LoggerMessage request) {

        Condition condition =  Condition.create();
        if (!StringUtils.isEmpty(request.getDomain())) {
            condition.where("domain = {0}", request.getDomain());
        }
        condition.and("ticket != 1");

        baseMapper.delete(condition);

        return true;
    }

    @Override
    public void ticket(TbLog request) {
        baseMapper.updateById(request);
    }

    @Override
    public TbLog findByUrl(TbLog request) {

        Condition condition =  Condition.create();

        if (!StringUtils.isEmpty(request.getUrl())){
            condition.and("url = {0}",  request.getUrl());
        }

        List desc = new ArrayList();
        desc.add("update_time");
        condition.orderDesc(desc);
        List<TbLog> logs = baseMapper.selectList(condition);
        if (logs.size()>0){
            return logs.get(0);
        }
        return null;
    }

    @Override
    public List<TbLog> groupBy(String name) {
        Condition condition =  Condition.create();
        condition.groupBy(name);
        List<TbLog> groups = baseMapper.selectList(condition);
        return groups;
    }


}
