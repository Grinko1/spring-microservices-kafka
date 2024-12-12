package com.example.orders.service;

import com.example.orders.dto.OrderDto;
import com.example.orders.model.Order;
import com.example.orders.model.Status;
import com.example.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository repository;
    private final ProducerService producerService;

    public List<Order> findAll() {
        List<Order> orders = repository.findAll();
        logger.info("Got list of orders: {}", orders);
        return orders;
    }

    public Order findById(Long id) {
        logger.info("Got order by Id: {} from BD", id);
        return repository.findById(id).orElseThrow();
    }

    public Order createOrder(Order newOrder) {
        newOrder.setStatus(Status.NEW);
        Order order = repository.save(newOrder);
        logger.info("Created new order : {}", order);
        producerService.sendOrderToKafka(new OrderDto(order.getId(), order.getUserId(), order.getStatus().name(), order.getTotalSum()));
        return order;
    }

    public Order updateStatus(Long id, Status newStatus) {
        Order order = findById(id);
        order.setStatus(newStatus);
        logger.info("Order with ID: {} change status to: {}", order.getId(), newStatus);
        return repository.save(order);
    }
}
