package com.nsu.habbitrabbit.controller.dto;

public class UserAuthOutput {
    private String jwt;
    private String error;

    public UserAuthOutput(String jwt) {
        this.jwt = jwt;
        error = null;
    }

    public UserAuthOutput(String jwt, String error) {
        this.jwt = jwt;
        this.error = error;
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
