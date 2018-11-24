package com.tp.api.controler;


import com.tp.api.mode.BaseResponse;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;


@Slf4j
@Controller
public class DemoControl {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    int i = 0 ;

    @RequestMapping("/kafka")
    @ResponseBody
    public BaseResponse sayHello() throws NotFoundException {

        try {
            kafkaTemplate.send("i18n", (int) (System.currentTimeMillis()%2),"key", "hello world" +i++);
            log.info("发送kafka成功.");
        } catch (Exception e) {
            log.error("发送kafka失败", e);
        }

        return new BaseResponse();
    }
}
