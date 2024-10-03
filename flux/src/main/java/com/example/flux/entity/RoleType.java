package com.example.flux.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
    ROLE_USER, ROLE_MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
