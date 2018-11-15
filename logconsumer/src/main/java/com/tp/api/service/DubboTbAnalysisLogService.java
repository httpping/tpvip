package com.tp.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.tp.api.entity.TbAnalysisLog;
import com.tp.api.mode.AnalysLogRequest;
import com.tp.api.mode.EchartBean;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DubboTbAnalysisLogService {
    
    @Reference
    TbAnalysisLogService tbAnalysisLogService;

    
    public List<TbAnalysisLog> getGroupBy(String name) {
        return tbAnalysisLogService.getGroupBy(name);
    }

    
    public Page<TbAnalysisLog> getLastMonth(TbAnalysisLog tbAnalysisLog) {
        return tbAnalysisLogService.getLastMonth(tbAnalysisLog);
    }

    
    public EchartBean createEchartBean(AnalysLogRequest analysLogRequest) {
        return tbAnalysisLogService.createEchartBean(analysLogRequest);
    }

    
    public TbAnalysisLog saveAndUpdate(TbAnalysisLog tbAnalysisLog) {
        return tbAnalysisLogService.saveAndUpdate(tbAnalysisLog);
    }

    
    public TbAnalysisLog getMaxChart() {
        return tbAnalysisLogService.getMaxChart();
    }

    
    public TbAnalysisLog getCurDaySumChart() {
        return tbAnalysisLogService.getCurDaySumChart();
    }
}
