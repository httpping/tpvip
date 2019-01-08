package com.tp.api.kafka;

import com.alibaba.fastjson.JSON;
import com.tp.api.dbservice.DbTbStringService;
import com.tp.api.entity.TbString;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerListener {




    @Autowired
    DbTbStringService dbTbStringService;

    @KafkaListener(topicPartitions = {@TopicPartition(topic = "i18n",partitions ={"0","1","2","3"})})
    public void listen(ConsumerRecord<String, ?> record) {
        log.info("kafka的key: " + record.key()+ record.partition());
        log.info("kafka的value: " + record.value().toString());
        String key = record.key();
        String value = record.value().toString();

        TbString tbString = (TbString) JSON.parseObject(value,TbString.class);
        //s升级
        try {
            dbTbStringService.saveOrUpdate(tbString);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}