package com.tp.api.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class TestServiceCom {

    @Reference
    TestService testService;

    public String sayHello(){
        testService.demo("xxx");
        return  "";
    }

}
