package com.example.booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;


@Getter
public enum UserRole {
    ROLE_USER, ROLE_ADMIN;
}
