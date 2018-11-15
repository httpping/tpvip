package com.tp.api.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Service
@Component
public class TestServiceImpl implements TestService {
    @Override
    public String demo(String de) {
        log.info("test demo");

        return "hello";
    }
}
