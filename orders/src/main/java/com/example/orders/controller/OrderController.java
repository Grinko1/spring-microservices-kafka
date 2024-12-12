package com.example.orders.controller;

import com.example.orders.model.Order;
import com.example.orders.model.Status;
import com.example.orders.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> findAll(){
        return orderService.findAll();
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findById(@PathVariable Long orderId){
        return new ResponseEntity<>(orderService.findById(orderId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order){
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }
    @PatchMapping("/{orderId}")
    public ResponseEntity<Order> updateStatus(@PathVariable Long orderId, @RequestBody Status status){
        return new ResponseEntity<>(orderService.updateStatus(orderId, status), HttpStatus.OK);
    }
}
