package com.nsu.habbitrabbit.domain;

import org.hibernate.annotations.Table;
import org.springframework.security.core.GrantedAuthority;


public class Role implements GrantedAuthority {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_USER_TITLE = "USER";
    public static final Long ROLE_USER_ID = 1L;
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_ADMIN_TITLE = "ADMIN";
    public static final Long ROLE_ADMIN_ID = 2L;

    String val;

    public Role(String val) {
        this.val = val;
    }

    @Override
    public String getAuthority() {
        return val;
    }
}