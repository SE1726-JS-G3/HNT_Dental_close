package com.hnt.dental.constant;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    ROLE_ADMIN, ROLE_MARKETING, ROLE_STAFF, ROLE_PATIENT;

    @Override
    public String getAuthority() {
        return name();
    }

    public static RoleEnum getRole(String role) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.name().equals(role)) {
                return roleEnum;
            }
        }
        return null;
    }
}