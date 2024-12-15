package com.example.payment.service;

import com.example.payment.dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public PaymentService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = objectMapper;
    }



    @Retryable(
            retryFor = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    @KafkaListener(topics = "new_orders", groupId = "payment-service-group")
    public void processPayment(String msg, Acknowledgment acknowledgment) {
        try {
            OrderDto order = mapper.readValue(msg, OrderDto.class);
            logger.info("Payment get order with Id : {}  from order", order.getId());
            acknowledgment.acknowledge();
            order.setStatus("PAYED");
            String responseMsg = mapper.writeValueAsString(order);
            // some logic with payment process ....

            kafkaTemplate.send("payed_orders",responseMsg );
            //msg for update status
            kafkaTemplate.send("update_order_status", responseMsg);

            logger.info("Sent order: {} to 'payed_orders'", order);
        } catch (Exception e) {
            logger.error("Error sent order to 'payed_orders'  {}", e.getMessage());
        }
    }
}