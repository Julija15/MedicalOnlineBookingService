package com.example.medicalonlinebookingservice.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    PATIENT,
    DOCTOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
