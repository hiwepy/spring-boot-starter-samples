package com.github.hiwepy.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducer {

    private final static String TOPIC_NAME = "zhTest"; //topic的名称

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public void send() {
        //发送功能就一行代码~
        kafkaTemplate.send(TOPIC_NAME,  "key", "test message send~");
    }

}
