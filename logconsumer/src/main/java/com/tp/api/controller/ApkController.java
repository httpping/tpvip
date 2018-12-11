package com.tp.api.controller;


import com.alibaba.fastjson.JSON;
import com.tp.api.mode.JenkindsBeanRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * apk 打包管理
 */
@Slf4j
@Controller
@RequestMapping("/apk")
public class ApkController {


    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestBody JenkindsBeanRequest jenkindsBeanRequest){

        log.info(JSON.toJSONString(jenkindsBeanRequest));

        return "success";
    }
}
