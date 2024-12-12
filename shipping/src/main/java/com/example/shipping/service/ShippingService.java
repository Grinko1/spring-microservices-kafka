package com.example.shipping.service;

import com.example.shipping.dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private static final Logger logger = LoggerFactory.getLogger(ShippingService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public ShippingService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "payed_orders", groupId = "shipping-service-group")
    public void shipOrder(String msg) {
        try {
            OrderDto order = mapper.readValue(msg, OrderDto.class);
            logger.info("Get order with Id from payment: {}", order.getId());
            //some process with shipping
            // bad approach just for imitate hard work 5s
            // Thread.sleep(5000);
            order.setStatus("DELIVERED");
            String responseMsg = mapper.writeValueAsString(order);
            kafkaTemplate.send("sent_orders",responseMsg );
            kafkaTemplate.send("update_order_status", responseMsg);
            logger.info("Sent order : {} to Kafka topic 'sent_orders'", order);
        } catch (Exception e) {
            logger.error("Error Sent to Kafka topic 'sent_order {}", e.getMessage());
        }
    }
}