package com.nsu.habbitrabbit.controller.dto;

public class CreatePlayerInput {

    private String name;
    private String email;
    private String password;


    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
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

    public CreatePlayerInput(final String name, final String email, final String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public CreatePlayerInput() {
    }
}
