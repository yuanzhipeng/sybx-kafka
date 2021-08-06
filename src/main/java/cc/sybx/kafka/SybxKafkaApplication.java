package cc.sybx.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@SpringBootApplication
public class SybxKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SybxKafkaApplication.class, args);
    }


    @Autowired
    private KafkaTemplate kafkaTemplate;

    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    static AtomicLong in  = new AtomicLong();

    @PostConstruct
    public void consProducerStart(){
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String simpleMsg = "这是一条测试消息 " + String.valueOf(in.getAndAdd(1));
                    ProducerRecord<String, String> record = new ProducerRecord<>("test_001", "0000", simpleMsg);
                    kafkaTemplate.send(record);
                }
            }
        });
    }
}
