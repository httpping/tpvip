package com.tp.api.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.tp.api.entity.ApiModel;
import com.tp.api.entity.TbLog;
import com.tp.api.mode.BaseResponse;
import com.tp.api.mode.GroupByRequest;
import com.tp.api.mode.LoggerMessage;
import com.tp.api.service.ApiModelService;
import com.tp.api.service.TbLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.tp.api.mode.BaseResponse.SUCCESS;


/**
 * <p>
 *  前端控制器 图片
 * </p>
 *
 * @author tanping
 * @since 2018-09-19
 */
@Slf4j
@Controller
@RequestMapping("/api")
public class TbLogController {

    @Reference(timeout = 10000,filter = "tracing")
    TbLogService tbLogService;

    @Reference(timeout = 10000)
    ApiModelService apiModelService;

    @GetMapping
    public String api(Model model,TbLog request){

        List<TbLog> result = tbLogService.findAll(request);

        model.addAttribute("domain",request.getDomain());
        model.addAttribute("url",request.getUrl());

        model.addAttribute("logs",result);
        model.addAttribute("headerTitle","Api 历史记录");
        return "/api";
    }


    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id){
        log.info("delete");

        tbLogService.deleteById(id);
        return "redirect:/api";
    }



    @PostMapping("/ticket")
    @ResponseBody
    public BaseResponse thicket(@RequestBody TbLog request){
        log.info("thicket" + request);
        tbLogService.ticket(request);
        return BaseResponse.created(SUCCESS,"message.common.success");
    }


    @RequestMapping("/model/{id}")
    public String oneKeyModel(Model model, @PathVariable("id") long id){
        log.info("oneKeyModel");
        TbLog log = tbLogService.selectById(id);

        ApiModel apiModel = new ApiModel();
        apiModel.setApiname(log.getApiname());
        apiModel.setResponse(log.getResponse());
        apiModel.setPlatform(log.getPlatform());
        apiModel.setDomain(log.getDomain());
        apiModel.setRequest(log.getRequest());

        String path = URI.create(log.getUrl()).getPath().toLowerCase();
        apiModel.setUrl(path);

        apiModel = apiModelService.save(apiModel);


        return "redirect:/model/edit/"+apiModel.getId().toString();
    }


    @PostMapping("/deleteAll")
    @ResponseBody
    public LoggerMessage deleteAll(@RequestBody LoggerMessage tbLog){
        log.info("deleteAll");

        tbLogService.deleteAll(tbLog);
        return tbLog;
    }

    @RequestMapping("/show/{id}")
    public String showId(Model model, @PathVariable("id") long id,TbLog request){
        log.info("show ----");
        TbLog log =  tbLogService.selectById(id);

        model.addAttribute("log",log);

        model.addAttribute("headerTitle","Api 接口详情");

        return "/api_show";
    }

    @RequestMapping("/show")
    public String showByUrl(Model model,TbLog request){
        log.info("show ----");
        TbLog log =  tbLogService.findByUrl(request);
        model.addAttribute("log",log);
        model.addAttribute("headerTitle","Api 接口详情");

        return "/api_show";
    }



    @PostMapping("/getGroupBy")
    @ResponseBody
    public List<TbLog> groupBy(@RequestBody GroupByRequest group){
        List<TbLog> result = tbLogService.groupBy(group.group);
        return result;
    }
}


