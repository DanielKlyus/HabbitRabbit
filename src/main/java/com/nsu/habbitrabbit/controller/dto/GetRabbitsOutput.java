package com.nsu.habbitrabbit.controller.dto;

public class GetRabbitsOutput {
    private Integer countOfRabbits;

    public GetRabbitsOutput() {
    }

    public GetRabbitsOutput(Integer countOfRabbits) {
        this.countOfRabbits = countOfRabbits;
    }

    public Integer getCountOfRabbits() {
        return countOfRabbits;
    }

    public void setCountOfRabbits(Integer countOfRabbits) {
        this.countOfRabbits = countOfRabbits;
    }
}
