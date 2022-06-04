package com.nsu.habbitrabbit.controller.dto;

public class DeletePlayerOutput {
    private boolean ok;
    private String error;

    public DeletePlayerOutput() {}

    public DeletePlayerOutput(boolean ok, String error) {
        this.ok = ok;
        this.error = error;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
