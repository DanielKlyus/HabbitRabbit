package com.nsu.habbitrabbit.controller.dto;


public class CreatePlayerOutput {

    private int id;
    private String name;
    private String email;
    private String password;
    private boolean isActive;
    private long updatedAt;
    private long createdAt;

    public int getId() {
        return this.id;
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

    public long getUpdatedAt() {
        return this.updatedAt;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setId(final int id) {
        this.id = id;
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

    public void setUpdatedAt(final long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(final long createdAt) {
        this.createdAt = createdAt;
    }

    public CreatePlayerOutput(final int id, final String name, final String email, final String password, final boolean isActive, final long updatedAt, final long createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }
}
