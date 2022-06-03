package com.nsu.habbitrabbit.controller.dto;

public class GetRoomInput {
    private Long roomId;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public GetRoomInput() {}

    public GetRoomInput(Long roomId) {
        this.roomId = roomId;
    }
}
