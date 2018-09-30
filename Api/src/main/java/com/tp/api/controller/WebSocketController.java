package com.tp.api.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.tp.api.config.websocket2.MyWebSocket;
import com.tp.api.entity.TbLog;
import com.tp.api.mode.LoggerMessage;
import com.tp.api.service.TbLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Api(value = "上传推送接口 -- 重要",description = "上传推送接口 -- 重要")
@Controller
public class WebSocketController {

    public static String topic = "/topic/pullLogger";

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Autowired
    TbLogService tbLogService;



    @ApiOperation(value = "上传推送日志接口-- 只需要用这个其他的忽略")
    @PostMapping("/pullLogcat")
    @ResponseBody
    public String logCat(@RequestBody LoggerMessage logcat){
//        System.out.println(logcat.body);

        try {

            //需要统计
            if (logcat.getUrl()!=null){
                TbLog tbLog = new TbLog();
                tbLog.setUrl(logcat.getUrl());
                tbLog.setDomain(logcat.getDomain());
                tbLog.setPlatform(logcat.platform);
                tbLog.setRequest(logcat.getRequest());
                tbLog.setResponse(logcat.getResponse());
                tbLog.setVersion(logcat.getVersion());
                tbLog.setFeeTime(logcat.getFeeTime());
                tbLog.setId(IdWorker.getId());
                exce(tbLog);
                String url = URLEncoder.encode(tbLog.getUrl(),"utf-8");
                String link = "&nbsp;&nbsp;&nbsp;<a href='/api/show?url=" +url+"' target='_blank'>查看格式化详情</a>" ;
                logcat.setBody(link + logcat.getBody() +" <br/>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //发送消息
        try {
            String json = JSON.toJSON(logcat).toString();
            MyWebSocket.sendInfo(json);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  "success";
    }

    private void exce(final TbLog tbLog){
        if (executorService == null){
            executorService = Executors.newFixedThreadPool(5);
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                  tbLogService.save(tbLog);
            }
        });
    }
}
