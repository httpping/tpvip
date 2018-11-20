package com.tp.api.dbservice.impl;


import com.baomidou.mybatisplus.mapper.Condition;
import com.tp.api.dbservice.DbTbAnalysisLogService;
import com.tp.api.dbservice.DbTbLogService;
import com.tp.api.entity.TbAnalysisLog;
import com.tp.api.mode.LoggerMessage;
import com.tp.api.service.TbAnalysisLogService;
import com.tp.api.service.TbLogService;
import com.tp.api.utils.DateUtis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@EnableScheduling
public class ScheduleServiceImpl {

    @Autowired
    DbTbLogService tbLogService;

    @Autowired
    DbTbAnalysisLogService tbAnalysisLogService;


    public static final  int MAX_COUNT = 5000;

    @Scheduled(cron = "0 15 7 * * ? ")
    public void deleteLog(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());

        try {
            tbLogService.deleteAll(new LoggerMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 工作时间每隔 半小时检查下 日志数量
     */
    @Scheduled(cron = "0 0/30 9-18 * * *")
//    @Scheduled(cron = "0/5 * * * * *")
    public void deleteMaxLog(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());

        try {
            Condition condition =  Condition.create();

            int tbLogs =  tbLogService.selectCount(condition);
            log.info("all count : "+tbLogs);
            //日志太多// 清理
            if (tbLogs > MAX_COUNT){
                deleteLog();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 凌晨 0:01 执行插入当前统计记录,防止重复问题
     */
    @Scheduled(cron = "1 1 0 * * ? ")
    public void createTbanalysis(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());

        try {
            List<TbAnalysisLog> tbAnalysisLogs =  tbAnalysisLogService.getGroupBy("domain");

            for (TbAnalysisLog tbAnalysisLog : tbAnalysisLogs){
                tbAnalysisLog.setUpdateDate(new Date());
                tbAnalysisLog.setMonthDay(DateUtis.formatMMDD());
                tbAnalysisLog.setCount(0);
                tbAnalysisLog.setMonth(DateUtis.getCurrentMonth()+"");
                tbAnalysisLog.setDay(DateUtis.getCurrentDay()+"");
                tbAnalysisLogService.saveAndUpdate(tbAnalysisLog);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
