package com.nsu.habbitrabbit.controller.dto;

public class ChangePlayerInput {
    private String name;
    private String password;


    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    public void setName(String newName) {
        this.name = newName;
    }


    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public ChangePlayerInput(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public ChangePlayerInput() {
    }
}
