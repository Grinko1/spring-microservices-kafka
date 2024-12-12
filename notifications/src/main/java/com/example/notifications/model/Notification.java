package com.example.notifications.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Notification {
    private Long id;
    private Long userId;
    private Long orderId;
    private String status;
    private String message;

}