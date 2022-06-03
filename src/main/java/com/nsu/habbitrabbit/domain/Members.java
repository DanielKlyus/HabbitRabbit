package com.nsu.habbitrabbit.domain;

import javax.persistence.*;

@Entity
@Table(name="members")
public class Members {
    @Id
    private Long roomId;
    @Id
    private Long playerId;

    public Members() {}

    public Members(Long roomId, Long playerId) {
        this.roomId = roomId;
        this.playerId = playerId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
