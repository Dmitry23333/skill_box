package com.example.rest.demo.web.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpsertNewsRequest {

    private Long userId;

    private String product;

    private BigDecimal cost;
}
