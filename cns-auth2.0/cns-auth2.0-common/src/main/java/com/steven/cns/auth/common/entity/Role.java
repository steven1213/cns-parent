package com.steven.cns.auth.common.entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return null;
    }
}
