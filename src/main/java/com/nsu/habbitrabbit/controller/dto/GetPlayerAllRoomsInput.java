package com.nsu.habbitrabbit.controller.dto;

public class GetPlayerAllRoomsInput {
    Long playerId;

    public GetPlayerAllRoomsInput() {
    }

    public GetPlayerAllRoomsInput(Long playerId) {
        this.playerId = playerId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
