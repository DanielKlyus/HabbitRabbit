package com.nsu.habbitrabbit.domain.visits;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.domain.visits.RoomActivity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name = "visits")
public class Visits {
    @Id
    @JoinColumn(name = "playerId")
    private Long playerId;
    //    @Type(type = "JsonObject")
//    ArrayList<RoomActivity> activities;
    String activities;

    public Visits() {
    }

    public Visits(
            final Long playerId,
            final String activities
    ) {
        this.playerId = playerId;
        this.activities = activities;
    }

    public Long getId() {
        return playerId;
    }

    public void setId(Long id) {
        this.playerId = id;
    }

    public ArrayList<RoomActivity> getActivities() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, RoomActivity> map = new LinkedHashMap<>();
        try {

            map = mapper.readValue(activities, new TypeReference<Map<String, RoomActivity>>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println("GARK while reading json");
        }
        ArrayList<RoomActivity> list = new ArrayList<>();
        for (var activityEntry : map.entrySet()) {
            list.add(activityEntry.getValue());
        }
        return list;
    }

    public void setActivities(ArrayList<RoomActivity> activities) {
        Map<String, RoomActivity> map = new LinkedHashMap<>();
        for (RoomActivity activity : activities) {
            map.put(activity.getRoomId().toString(), activity);
        }
        try {
            this.activities = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            System.out.println("GARK while processing json");
        }
    }

}
