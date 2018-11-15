package com.tp.api;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.tp.api.mapper")
@EnableCaching
@Slf4j
public class ProviderApplication {
    public static void main(String[] args) {

        log.info("start");
        SpringApplication.run(ProviderApplication.class, args);
    }





}