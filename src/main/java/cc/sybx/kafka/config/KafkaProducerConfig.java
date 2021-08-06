package cc.sybx.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.RoundRobinPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;

/**
 * @date 2021/8/6 14:52
 */
@Configuration
public class KafkaProducerConfig {
    @Value("${kafka.bootstrap_servers_config}")
    private String bootstrap_servers_config;


    @Bean
    public KafkaTemplate kafkaTemplate(){
        HashMap<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrap_servers_config);
        configs.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class);
        //设置序列化
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        //设置自定义分区
        DefaultKafkaProducerFactory producerFactory = new DefaultKafkaProducerFactory(configs);
        return new KafkaTemplate(producerFactory);
    }
}
