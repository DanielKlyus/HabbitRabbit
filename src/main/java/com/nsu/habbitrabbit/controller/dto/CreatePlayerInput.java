package com.nsu.habbitrabbit.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerInput {

    private String name;
    private String email;
    private String password;
    private Boolean isAdmin;


//    public String getName() {
//        return this.name;
//    }
//
//    public String getEmail() {
//        return this.email;
//    }
//
//    public String getPassword() {
//        return this.password;
//    }
//
//    public void setName(final String name) {
//        this.name = name;
//    }
//
//    public void setEmail(final String email) {
//        this.email = email;
//    }
//
//    public void setPassword(final String password) {
//        this.password = password;
//    }
//
//    public boolean isAdmin() {
//        return isAdmin;
//    }
//
//    public void setAdmin(Boolean isAdmin) {
//        isAdmin = isAdmin;
//    }
//
//    public CreatePlayerInput(final String name, final String email, final String password, final Boolean isAdmin) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.isAdmin = isAdmin;
//    }
//
//    public CreatePlayerInput() {
//    }
}
