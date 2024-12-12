package com.example.orders.config;

import com.example.orders.model.Order;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@Configuration
@EnableKafka
public class KafkaConfig {
//    @Bean
//    public NewTopic topic1() {
//        return new NewTopic("new_orders", 1, (short) 1);
//    }
//
//    @Bean
//    public NewTopic topic2() {
//        return new NewTopic("payed_orders", 1, (short) 1);
//    }
//
//    @Bean
//    public NewTopic topic3() {
//        return new NewTopic("sent_orders", 1, (short) 1);
//    }

//    @Bean
//    public Executor taskExecutor() {
//        return new SimpleAsyncTaskExecutor();
//    }
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfig());
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    private Map<String, Object> producerConfig() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return config;
//    }
}
