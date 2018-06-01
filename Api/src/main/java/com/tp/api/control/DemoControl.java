package com.tp.api.control;


import com.tp.common.bean.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class DemoControl {


    @PostMapping("/")
    public BaseResult sayHello(){
        log.info("hello world!");
        BaseResult baseResult = new BaseResult();
        baseResult.data = "hello word";
        return  baseResult;
    }
}
