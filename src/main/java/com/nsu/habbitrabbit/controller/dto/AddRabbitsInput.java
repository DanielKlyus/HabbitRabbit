package com.nsu.habbitrabbit.controller.dto;

public class AddRabbitsInput {

    Long playerID;
    Integer rabbitsToAdd;

    public AddRabbitsInput() {
    }

    public AddRabbitsInput(Long playerID, Integer rabbitsToAdd) {
        this.playerID = playerID;
        this.rabbitsToAdd = rabbitsToAdd;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }

    public Integer getRabbitsToAdd() {
        return rabbitsToAdd;
    }

    public void setRabbitsToAdd(Integer rabbitsToAdd) {
        this.rabbitsToAdd = rabbitsToAdd;
    }
}
