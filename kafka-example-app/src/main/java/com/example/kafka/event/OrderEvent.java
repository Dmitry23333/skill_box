package com.example.kafka.event;

import lombok.Data;

@Data
public class OrderEvent {
    private String product;
    private Integer quantity;
}
