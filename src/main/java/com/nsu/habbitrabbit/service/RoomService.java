package com.nsu.habbitrabbit.service;

import com.nsu.habbitrabbit.controller.dto.CreateRoomInput;
import com.nsu.habbitrabbit.controller.dto.CreateRoomOutput;
import com.nsu.habbitrabbit.domain.Room;
import com.nsu.habbitrabbit.repo.RoomRepository;
import com.nsu.habbitrabbit.service.mapper.room.CreateRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoomService {
    RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public CreateRoomOutput createRoom(CreateRoomInput input){
        Room current = new Room();
        current.setName(input.getName());
        current.setDescription(input.getDescription());
        current.setCreatorId(input.getCreatorId());
        current.setCreatedAt(new Date());
        current.setUpdatedAt(new Date());
        current.setFinishedAt(input.getFinishedAt());
        current.setRabbitsForFailure(input.getRabbitsForFailure());
        current.setRabbitsForSuccess(input.getRabbitsForSuccess());

        current = roomRepository.save(current);
        return CreateRoomMapper.mapRoomToDTO(current);
    }
}


