package com.nsu.habbitrabbit.controller.dto;

public class UserAuthOutput {
    private Long id;
    private String name;
    private String email;
    private String jwt;
    private String error;

    public UserAuthOutput(Long id, String name, String email, String jwt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.jwt = jwt;
        error = null;
    }

    public UserAuthOutput(String jwt, String error) {
        this.jwt = jwt;
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

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
