package com.tp.api.controller;


import com.tp.api.entity.ApiModel;
import com.tp.api.service.ApiModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 *  前端控制器 api 模拟中心
 * </p>
 *
 * @author tanping
 * @since 2018-09-21
 */
@Slf4j
@Controller
@RequestMapping("/model")
public class ApiModelController {

    @Autowired
    ApiModelService apiModelService;

    @GetMapping
    public String api(Model model, ApiModel request){

        List<ApiModel> result = apiModelService.findAll(request);
        model.addAttribute("request",request);
        model.addAttribute("models",result);
        model.addAttribute("headerTitle","Api-模拟系统");

        return "/model/index";
    }

    @GetMapping("/edit")
    public String edit(Model model, ApiModel request){
        log.info(request.toString());

        if (request.getId() !=null){
            request = apiModelService.findById(request);
        }

        model.addAttribute("model",request);
        model.addAttribute("success",false);
        model.addAttribute("headerTitle","Api-模型编辑");


        return "/model/edit";
    }

    @GetMapping("/edit/{id}")
    public String editForId(Model model, @PathVariable("id") long id, ApiModel request){
         request.setId(id);
         return  edit(model,request);
    }

    @PostMapping("/edit")
    public String editSave(Model model, ApiModel request){
        log.info(request.toString());
        apiModelService.save(request);
        model.addAttribute("model",request);
        model.addAttribute("success",true);
        model.addAttribute("headerTitle","Api-模型编辑");

        return "/model/edit";
    }
}

