package com.nsu.habbitrabbit.controller.dto;

public class DeletePlayerInput {
    Long playerId;

    public DeletePlayerInput() {}

    public DeletePlayerInput(Long playerId) {
        this.playerId = playerId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
