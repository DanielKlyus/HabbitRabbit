package com.nsu.habbitrabbit.controller.dto;

import java.util.Date;

public class ChangePlayerOutput {
    private Long id;
    private String name;
    private String email;
    private boolean isActive;
    private Date updatedAt;
    private Date createdAt;
    private String error;

    public ChangePlayerOutput(Long id,
                              String name,
                              String email,
                              boolean isActive,
                              Date updatedAt,
                              Date createdAt,
                              String error) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.error = error;
    }

    public ChangePlayerOutput(Long id,
                              String name,
                              String email,
                              boolean isActive,
                              Date updatedAt,
                              Date createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public ChangePlayerOutput(String error) {
        this.error = error;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
