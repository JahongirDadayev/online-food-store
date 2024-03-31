package com.example.onlinefoodstore.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum EAuthority implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
