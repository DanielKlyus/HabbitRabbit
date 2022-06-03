package com.nsu.habbitrabbit.repo;

import com.nsu.habbitrabbit.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, Long>{
    public Room findRoomByName(String name);
    public Room findRoomById(Long id);
    ArrayList<Room> findAllByIdIn(ArrayList<Long> ids);
}
