package com.example.orders.service;

import com.example.orders.dto.OrderDto;
import com.example.orders.model.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderService orderService;
    private final ObjectMapper mapper;


    @KafkaListener(topics = "update_order_status", groupId = "orders-service-group")
    public void updateStatus(String msg) throws JsonProcessingException {

        OrderDto order = mapper.readValue(msg, OrderDto.class);

        try {
            if (order.getStatus().equals(Status.PAYED.name())) {
                logger.info("Order with ID :{} was payed ", order.getId());
                orderService.updateStatus(order.getId(), Status.PAYED);
            } else if (order.getStatus().equals(Status.DELIVERED.name())) {
                logger.info("Order with ID :{} was delivered ", order.getId());
                orderService.updateStatus(order.getId(), Status.DELIVERED);
            }
        } catch (Exception e) {
            logger.error("Fail change status to order with ID  {}, {}", order.getId(), e.getMessage());
        }
    }

}
