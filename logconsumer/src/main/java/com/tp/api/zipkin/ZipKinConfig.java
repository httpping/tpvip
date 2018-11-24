package com.tp.api.zipkin;

import brave.spring.beans.TracingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.beans.AsyncReporterFactoryBean;
import zipkin2.reporter.beans.OkHttpSenderFactoryBean;
@Configuration
public class ZipKinConfig {



    @Bean
    public OkHttpSenderFactoryBean sender(){
        OkHttpSenderFactoryBean bean = new OkHttpSenderFactoryBean();
        bean.setEndpoint("http://10.32.2.253:9411/api/v2/spans");
        return bean;
    }

    @Bean(name = "tracing")
    public TracingFactoryBean tracingFactoryBean(Reporter reporter){
        TracingFactoryBean bean = new TracingFactoryBean();
        bean.setLocalServiceName("demo-client");
        bean.setSpanReporter(reporter);
        return  bean;
    }


    @Bean
    public AsyncReporterFactoryBean asyncReporterFactoryBean(Sender sender){
        AsyncReporterFactoryBean bean = new AsyncReporterFactoryBean();
        bean.setSender(sender);
        bean.setCloseTimeout(1000);

        return bean;
    }
}
