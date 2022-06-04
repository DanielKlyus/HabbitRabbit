package com.nsu.habbitrabbit.controller.dto;

public class DeleteRoomOutput {
    private boolean ok;
    private String error;

    public DeleteRoomOutput(boolean ok, String error) {
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
