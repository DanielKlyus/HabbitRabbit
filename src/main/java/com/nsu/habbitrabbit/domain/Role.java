package com.nsu.habbitrabbit.domain;

import org.hibernate.annotations.Table;
import org.springframework.security.core.GrantedAuthority;


public class Role implements GrantedAuthority {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_USER_TITLE = "USER";
    public static final Long ROLE_USER_ID = 1L;
    public static final String ROLE_SYS_ADMIN = "ROLE_SYS_ADMIN";
    public static final String ROLE_SYS_ADMIN_TITLE = "SYS_ADMIN";
    public static final Long ROLE_SYS_ADMIN_ID = 2L;
    public static final String ROLE_ROOM_ADMIN = "ROLE_ROOM_ADMIN";
    public static final String ROLE_ROOM_ADMIN_TITLE = "ROOM_ADMIN";
    public static final Long ROLE_ROOM_ADMIN_ID = 3L;

    String val;

    public Role(String val) {
        this.val = val;
    }

    @Override
    public String getAuthority() {
        return val;
    }
}