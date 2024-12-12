package com.example.payment.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
   private Long id;
   private Long userId;
   private String status;
   private Long sum;
}