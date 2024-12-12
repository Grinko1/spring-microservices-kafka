package com.example.notifications.service;

import com.example.notifications.dto.OrderDto;
import com.example.notifications.model.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final ObjectMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @KafkaListener(topics = "sent_orders", groupId = "notification-service-group")
    public void sendNotification(String msg) {
        try {
            OrderDto order = mapper.readValue(msg, OrderDto.class);
            logger.info("Get order with id: {} from shipping", order.getId());
            // sent notification for example email
            logger.info("Notification: Your order : {}, was delivered", order);
        } catch (Exception e) {
            logger.error("Error sending notification, {}", e.getMessage());
        }
    }
}