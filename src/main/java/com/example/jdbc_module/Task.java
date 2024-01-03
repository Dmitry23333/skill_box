package com.example.jdbc_module;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.relational.core.mapping.Table;


@FieldNameConstants
@Data
@Table
public class Task {
    private long id;
    private String title;
    private String description;
    private int priority;
}
