package com.nsu.habbitrabbit.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(MembersId.class)
@Table(name="members")
public class Members implements Serializable {
    @Id
    @Column(name = "room_id")
    private Long roomId;
    @Id
    @Column(name = "player_id")
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
