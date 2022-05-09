package com.nsu.habbitrabbit.service.mapper.room;

import com.nsu.habbitrabbit.controller.dto.CreateRoomOutput;
import com.nsu.habbitrabbit.domain.Room;

public class CreateRoomMapper {
    public static CreateRoomOutput mapRoomToDTO(Room room){
        return new CreateRoomOutput(
                room.getId(),
                room.getName(),
                room.getCreatorId(),
                room.getCreatedAt(),
                room.getUpdatedAt(),
                room.getFinishedAt(),
                room.getRabbitsForFailure(),
                room.getRabbitsForSuccess()
        );
    }
}
