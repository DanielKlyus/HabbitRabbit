package com.nsu.habbitrabbit.controller.dto;

public class RemoveRabbitsInput {
    Long playerID;
    Integer rabbitsToRemove;

    public RemoveRabbitsInput() {
    }

    public RemoveRabbitsInput(Long playerID, Integer rabbitsToRemove) {
        this.playerID = playerID;
        this.rabbitsToRemove = rabbitsToRemove;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }

    public Integer getRabbitsToRemove() {
        return rabbitsToRemove;
    }

    public void setRabbitsToRemove(Integer rabbitsToRemove) {
        this.rabbitsToRemove = rabbitsToRemove;
    }
}
