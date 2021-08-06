package cc.sybx.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class SybxKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SybxKafkaApplication.class, args);
    }


    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    @PostConstruct
    public void consProducerStart(){

    }
}
