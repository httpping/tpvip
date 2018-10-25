package com.tp.api.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.tp.api.entity.TbAnalysisLog;
import com.baomidou.mybatisplus.service.IService;
import com.tp.api.mode.AnalysLogRequest;
import com.tp.api.mode.EchartBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tanping
 * @since 2018-10-19
 */
public interface TbAnalysisLogService extends IService<TbAnalysisLog> {


     List<TbAnalysisLog> getGroupDomain(String name );


     Page<TbAnalysisLog> getLastMonth(TbAnalysisLog tbAnalysisLog);


     EchartBean createEchartBean(AnalysLogRequest analysLogRequest);

     TbAnalysisLog saveAndUpdate(TbAnalysisLog tbAnalysisLog);


     TbAnalysisLog getMaxChart();
}
