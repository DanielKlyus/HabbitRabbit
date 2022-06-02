package com.nsu.habbitrabbit.domain.visits;

import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.domain.visits.RoomActivity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "visits")
public class Visits {
    @Id
    @OneToOne
    @JoinColumn(name = "playerId")
    private Player playerId;
    @Type(type = "JsonObject")
    ArrayList<RoomActivity> activities;

    public Visits() {
    }

    public Visits(
            final Player playerId,
            final ArrayList<RoomActivity> activities
    ) {
        this.playerId = playerId;
        this.activities = activities;
    }

    public Player getId() {
        return playerId;
    }

    public void setId(Player id) {
        this.playerId = id;
    }

    public ArrayList<RoomActivity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<RoomActivity> activities) {
        this.activities = activities;
    }

}
