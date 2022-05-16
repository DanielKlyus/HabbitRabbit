package com.nsu.habbitrabbit.controller.dto;

public class ChangePlayerInput {
    private String oldEmail;
    private String newEmail;
    private String newName;
    private String oldPassword;
    private String newPassword;

    public String getOldEmail() {
        return this.oldEmail;
    }

    public String getNewEmail() {
        return this.newEmail;
    }

    public String getNewName() {
        return newName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setOldEmail(final String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public void setNewEmail(final String newEmail) {
        this.newEmail = newEmail;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public ChangePlayerInput(String oldEmail, String newEmail, String newName, String oldPassword, String newPassword) {
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
        this.newName = newName;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ChangePlayerInput() {
    }
}
