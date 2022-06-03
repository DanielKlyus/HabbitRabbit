package com.nsu.habbitrabbit.controller.dto;

public class GetRabbitsInput {
    Long playerID;

    public GetRabbitsInput() {
    }

    public GetRabbitsInput(Long playerID) {
        this.playerID = playerID;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }
}
