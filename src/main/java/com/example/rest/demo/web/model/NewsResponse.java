package com.example.rest.demo.web.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewsResponse {
private Long id;
private String product;

private BigDecimal cost;

}
