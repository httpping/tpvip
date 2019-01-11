package com.tp.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.validation.MethodValidated;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tp.api.config.websocket2.MyWebSocket;
import com.tp.api.entity.TbLog;
import com.tp.api.mode.LoggerMessage;
import com.tp.api.service.TbLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.tp.api.zipkin.ZipKinConfig.FILTER;

@Api(value = "上传推送接口 -- 重要",description = "上传推送接口 -- 重要")
@Controller
public class WebSocketController {

    public static String topic = "/topic/pullLogger";

    ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Reference(timeout = 10000,filter = FILTER)
    TbLogService tbLogService;



    @HystrixCommand(fallbackMethod = "onError",
            threadPoolKey ="WebSocketController",
            groupKey = "WebSocketController",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5")},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "50"),
                    @HystrixProperty(name = "maxQueueSize", value = "150")
            })
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
//                tbLog = tbLogService.save(tbLog);
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
            MyWebSocket.sendInfo(json,logcat.getPlatform());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  "success";
    }

    public String onError(@RequestBody LoggerMessage logcat) {
        return "timeout";
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
