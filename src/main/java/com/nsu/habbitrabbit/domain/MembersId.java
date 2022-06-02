package com.nsu.habbitrabbit.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class MembersId implements Serializable {
    private Long roomId;
    private Long playerId;

    public MembersId(Long roomId, Long playerId) {
        this.roomId = roomId;
        this.playerId = playerId;
    }

    MembersId() {}

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
