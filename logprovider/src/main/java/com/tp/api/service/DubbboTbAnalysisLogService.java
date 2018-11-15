package com.tp.api.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.tp.api.dbservice.DbTbAnalysisLogService;
import com.tp.api.entity.TbAnalysisLog;
import com.tp.api.mode.AnalysLogRequest;
import com.tp.api.mode.EchartBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanping
 * @since 2018-10-19
 */
@Service
public class DubbboTbAnalysisLogService implements TbAnalysisLogService {

     @Autowired
     DbTbAnalysisLogService dbTbAnalysisLogService;
     @Override
     public List<TbAnalysisLog> getGroupBy(String name) {
          return dbTbAnalysisLogService.getGroupBy(name);
     }

     @Override
     public Page<TbAnalysisLog> getLastMonth(TbAnalysisLog tbAnalysisLog) {
          return dbTbAnalysisLogService.getLastMonth(tbAnalysisLog);
     }

     @Override
     public EchartBean createEchartBean(AnalysLogRequest analysLogRequest) {
          return dbTbAnalysisLogService.createEchartBean(analysLogRequest);
     }

     @Override
     public TbAnalysisLog saveAndUpdate(TbAnalysisLog tbAnalysisLog) {
          return dbTbAnalysisLogService.saveAndUpdate(tbAnalysisLog);
     }

     @Override
     public TbAnalysisLog getMaxChart() {
          return dbTbAnalysisLogService.getMaxChart();
     }

     @Override
     public TbAnalysisLog getCurDaySumChart() {
          return dbTbAnalysisLogService.getCurDaySumChart();
     }
}
