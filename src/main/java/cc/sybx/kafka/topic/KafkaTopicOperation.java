package cc.sybx.kafka.topic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2021/7/30 17:58
 */
@RestController
@RequestMapping("/kafka/operation")
public class KafkaTopicOperation {

    public String createTpoic(){

        return "200";
    }

}
