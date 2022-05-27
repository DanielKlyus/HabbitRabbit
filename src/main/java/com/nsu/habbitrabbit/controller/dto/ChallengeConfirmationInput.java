package com.nsu.habbitrabbit.controller.dto;

public class ChallengeConfirmationInput {
    private Long roomId;
    private Long playerId;

    public Long getRoomId() { return roomId; }

    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public Long getPlayerId() { return playerId; }

    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public ChallengeConfirmationInput(Long roomId, Long playerId){
        this.roomId = roomId;
        this.playerId = playerId;
    }

    public ChallengeConfirmationInput(){
    }
}
