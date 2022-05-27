package com.nsu.habbitrabbit.controller.dto;

public class ChallengeConfirmationOutput {
    private boolean ok;
    private String error;

    public ChallengeConfirmationOutput(boolean ok, String error) {
        this.ok = ok;
        this.error = error;
    }

    public ChallengeConfirmationOutput(boolean ok) {
        this.ok = ok;
    }

    public boolean isOk() { return ok; }

    public void setOk(boolean ok) { this.ok = ok; }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
