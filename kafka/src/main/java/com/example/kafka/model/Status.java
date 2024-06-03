package com.example.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Status {
    private String status;
    private Instant date;
}
