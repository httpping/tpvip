package com.tp.api.controller;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.baomidou.mybatisplus.toolkit.StringUtils;
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

    @Autowired
    //通过SimpMessagingTemplate模板向浏览器发送消息。如果是广播模式，可以直接使用注解@SendTo
    private SimpMessagingTemplate simpMessagingTemplate;

    //开启STOMP协议来传输基于代理的消息，这时控制器支持使用@MessageController，就像使用@RequestMapping是一样的
    //当浏览器向服务端发送请求时，通过@MessageController映射/chat这个路径
//    @MessageMapping("/chat")
    //在SpringMVC中，可以直接在参数中获得principal,其中包含当前用户的信息
    public void handleChat(Principal principal,String msg){
        /*
        //下面的代码就是如果发送人是Michael，接收人就是Janet，发送的信息是message，反之亦然。
        if(principal.getName().equals("Michael")){
            //通过SimpMessagingTemplate的convertAndSendToUser向用户发送消息。
            //第一参数表示接收信息的用户，第二个是浏览器订阅的地址，第三个是消息本身
            simpMessagingTemplate.convertAndSendToUser("Janet","/queue/notifications",
                    principal.getName() + "-发送:" + msg);
        } else {
            simpMessagingTemplate.convertAndSendToUser("Michael","/queue/notifications",
                    principal.getName() + "-发送:" + msg);
        }
        */

        simpMessagingTemplate.convertAndSend(topic,msg);
    }



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
                String link = "&nbsp;&nbsp;&nbsp;<a href='/api/show?url=" +url+"'>查看格式化详情</a>" ;
                logcat.setBody(link + logcat.getBody() +" <br/>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!StringUtils.isEmpty(logcat.getPlatform())){
            simpMessagingTemplate.convertAndSend(topic,logcat);
        }else {
            simpMessagingTemplate.convertAndSend(topic,logcat);
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
