package cc.sybx.kafka.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @date 2021/8/6 17:22
 */
@Slf4j
@Service
public class ProducerMsgThread implements Runnable {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void run() {
        String simpleMsg = "这是一条测试消息";

        ProducerRecord<String, String> record = new ProducerRecord<>("test_001", "0000", simpleMsg);
        kafkaTemplate.send(record);
    }
}
