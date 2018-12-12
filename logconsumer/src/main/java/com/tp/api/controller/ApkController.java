package com.tp.api.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.tp.api.constant.ReturnCodeEnum;
import com.tp.api.entity.ApkInfo;
import com.tp.api.mode.BaseResponse;
import com.tp.api.service.ApkInfosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * apk 打包管理
 */
@Slf4j
@Controller
@RequestMapping("/apk")
public class ApkController {


    @Reference
    ApkInfosService apkInfosService;

    @GetMapping
    public String api(Model model, ApkInfo request){
        List<ApkInfo> result = apkInfosService.apkGroup();
        model.addAttribute("request",request);
        model.addAttribute("models",result);
        model.addAttribute("headerTitle","Apk管理");

        return "/apk/index";
    }

    @GetMapping("/{name}")
    public String apiName(Model model, @PathVariable String name){
        ApkInfo apkInfo = new ApkInfo();
        apkInfo.setName(name);
        List<ApkInfo> result = apkInfosService.query(apkInfo);
        model.addAttribute("models",result);
        model.addAttribute("headerTitle",name);

        return "/apk/index-name";
    }


    @PostMapping("/group")
    @ResponseBody
    public BaseResponse apkGroup(@RequestBody ApkInfo request){
        List<ApkInfo> result = apkInfosService.apkGroup();
        BaseResponse response = new BaseResponse();
        response.data =result;
        response.code = ReturnCodeEnum.CODE_200.getCode();
        response.message =ReturnCodeEnum.CODE_200.getValue();
        return response;
    }

    @PostMapping("/group/detail")
    @ResponseBody
    public BaseResponse apkGroupDetail(@RequestBody ApkInfo request){
        List<ApkInfo> result = apkInfosService.query(request);
        BaseResponse response = new BaseResponse();
        response.data =result;
        response.code = ReturnCodeEnum.CODE_200.getCode();
        response.message =ReturnCodeEnum.CODE_200.getValue();
        return response;
    }


    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestBody ApkInfo apkInfo){
        apkInfosService.add(apkInfo);
        return "success";
    }
}
