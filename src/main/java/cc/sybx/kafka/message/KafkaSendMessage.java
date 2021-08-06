package cc.sybx.kafka.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

/**
 * @date 2021/8/6 14:13
 */
@Slf4j
@RestController
@RequestMapping("/kafka/send")
public class KafkaSendMessage {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/simple/msg")
    public String simpleMsg(){
        String simpleMsg = "这是一条测试消息";

        ProducerRecord<String, String> record = new ProducerRecord<>("test_001", "0000", simpleMsg);
        kafkaTemplate.send(record);
        return "200";
    }

    @KafkaListener(topics = "test_001")
    public void listen0(ConsumerRecords<String, String> records) {
        Iterator<ConsumerRecord<String, String>> res = records.iterator();
        while(res.hasNext()){
            ConsumerRecord<String, String> record = res.next();
            int partition = record.partition();
            String msg = record.value();
            long offset = record.offset();
            log.info("partition is:{}; msg:{}; offset is:{}", partition, msg, offset);
        }
    }
}
