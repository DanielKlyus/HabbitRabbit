package com.nsu.habbitrabbit.controller.dto;


import java.util.Date;

public class CreatePlayerOutput {

    private Long id;
    private String name;
    private String email;
    private boolean isActive;
    private Date updatedAt;
    private Date createdAt;
    private String error;


    public CreatePlayerOutput(
            final Long id,
            final String name,
            final String email,
            final boolean isActive,
            final Date updatedAt,
            final Date createdAt,
            final String error
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.error = error;
    }

    public CreatePlayerOutput(
            final Long id,
            final String name,
            final String email,
            final boolean isActive,
            final Date updatedAt,
            final Date createdAt
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public CreatePlayerOutput(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
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

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
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
}
