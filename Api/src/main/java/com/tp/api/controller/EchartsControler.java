package com.tp.api.controller;


import com.tp.api.entity.TbAnalysisLog;
import com.tp.api.entity.TbString;
import com.tp.api.mode.AnalysLogRequest;
import com.tp.api.mode.EchartBean;
import com.tp.api.mode.StringFilterRequestParam;
import com.tp.api.service.TbAnalysisLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/echarts")
public class EchartsControler {

    @Autowired
    TbAnalysisLogService tbAnalysisLogService;

    @GetMapping
    public String index(Model model, AnalysLogRequest param){
        model.addAttribute("headerTitle","Domain 统计报表");

        EchartBean echartBean =  tbAnalysisLogService.createEchartBean(param);
        model.addAttribute("echartData",echartBean);
        return "/echarts/index";
    }

    @GetMapping("/data")
    @ResponseBody
    public EchartBean data(Model model, AnalysLogRequest param){
        model.addAttribute("headerTitle","Domain 统计报表");
        EchartBean echartBean =  tbAnalysisLogService.createEchartBean(param);

        return  echartBean;
    }



    @PostMapping("/max")
    @ResponseBody
    public TbAnalysisLog maxData(Model model, AnalysLogRequest param){
        TbAnalysisLog tbAnalysisLog =  tbAnalysisLogService.getMaxChart();
        return  tbAnalysisLog;
    }

    @PostMapping("/sum_cur")
    @ResponseBody
    public TbAnalysisLog curDaySum(Model model, AnalysLogRequest param){
        TbAnalysisLog tbAnalysisLog =  tbAnalysisLogService.getCurDaySumChart();
        return  tbAnalysisLog;
    }
}
