package com.example.payment.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {
    private String orderId;
    private String paymentMethod;
    private double amount;
    private String status;

    // Getters and setters
}