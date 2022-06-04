package com.nsu.habbitrabbit.service.mapper.room;

import com.nsu.habbitrabbit.controller.dto.GetRoomOutput;
import com.nsu.habbitrabbit.domain.Room;

public class GetRoomMapper {
    public static GetRoomOutput mapRoomToDTO(Room room, Boolean isFinished, Boolean isClicked) {
        return new GetRoomOutput(
                room.getId(),
                room.getName(),
                room.getDescription(),
                room.getCreatorId(),
                room.getCreatedAt(),
                room.getUpdatedAt(),
                room.getFinishedAt(),
                room.getRabbitsForFailure(),
                room.getRabbitsForSuccess(),
                isFinished,
                isClicked
        );
    }
}
