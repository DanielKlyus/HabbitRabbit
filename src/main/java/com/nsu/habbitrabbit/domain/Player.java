package com.nsu.habbitrabbit.domain;

import java.util.Date;

public class Player {
    private String login;
    private String name;
    private String email;
    private String password;
    private boolean isActive;
    private Date updatedAt;
    private Date createdAt;

    public String getLogin() {
        return this.login;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setActive(final boolean isActive) {
        this.isActive = isActive;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public Player(final String login, final String name, final String email, final String password, final boolean isActive, final Date updatedAt, final Date createdAt) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }
}
