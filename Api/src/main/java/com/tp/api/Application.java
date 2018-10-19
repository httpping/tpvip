package com.tp.api;

import com.tp.api.fileupload.CustomMultipartResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.multipart.MultipartResolver;

@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CorsConfiguration corsConfiguration(){
        return new CorsConfiguration();
    }



//    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver()
    {
        return new CustomMultipartResolver();
    }

}