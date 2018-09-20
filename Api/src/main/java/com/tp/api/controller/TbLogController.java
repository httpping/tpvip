package com.tp.api.controller;


import com.tp.api.entity.TbLog;
import com.tp.api.mode.LogRequest;
import com.tp.api.mode.LoggerMessage;
import com.tp.api.service.TbLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

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

    @Autowired
    TbLogService tbLogService;

    @GetMapping
    public String api(Model model,TbLog request){

        List<TbLog> result = tbLogService.findAll(request);

        model.addAttribute("domain",request.getDomain());
        model.addAttribute("logs",result);

        return "/api";
    }


    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") long id){
        log.info("delete");

        tbLogService.deleteById(id);
        return "redirect:/api";
    }

    @PostMapping("/deleteAll")
    @ResponseBody
    public LoggerMessage deleteAll(@RequestBody LoggerMessage tbLog){
        log.info("deleteAll");

        tbLogService.deleteAll(tbLog);
        return tbLog;
    }

    @RequestMapping("/show/{id}")
    public String show(Model model, @PathVariable("id") long id){
        TbLog log =  tbLogService.selectById(id);

        model.addAttribute("log",log);
        return "/api_show";
    }
}


