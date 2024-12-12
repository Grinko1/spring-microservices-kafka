package com.example.orders.service;

import com.example.orders.dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public void sendOrderToKafka(OrderDto order) {
        try {
            String msg = objectMapper.writeValueAsString(order);
            logger.info("Sending order with ID: {} to Kafka topic 'new_orders'", order.getId());
            kafkaTemplate.send("new_orders",msg);
            logger.info("Order: {} successfully sent to Kafka topic 'new_orders'", order);
        } catch (Exception e) {
            logger.error("Error sending order with ID: {} to Kafka : 'new_orders'" , order.getId());
        }
    }

}
