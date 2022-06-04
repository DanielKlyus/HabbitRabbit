package com.nsu.habbitrabbit.controller;

import com.nsu.habbitrabbit.controller.dto.CreateRoomInput;
import com.nsu.habbitrabbit.controller.dto.CreateRoomOutput;
import com.nsu.habbitrabbit.controller.dto.GetRoomInput;
import com.nsu.habbitrabbit.controller.dto.GetRoomOutput;
import com.nsu.habbitrabbit.domain.Room;
import com.nsu.habbitrabbit.controller.dto.*;
import com.nsu.habbitrabbit.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("rooms/create")
    public CreateRoomOutput createRoom(@RequestBody CreateRoomInput input) {
        return roomService.createRoom(input);
    }

    @PostMapping("rooms/get_room")
    public GetRoomOutput getRoom(@RequestBody GetRoomInput input) {
        return roomService.getRoom(input);
    }

    @PostMapping("rooms/get_rooms/{playerId}")
    public ArrayList<GetRoomOutput> getRoom(@PathVariable Long playerId) {
        var res = new ArrayList<GetRoomOutput>();
        var a = roomService.getRooms(playerId);
        for(var item : a) {
            res.add(roomService.getRoom(new GetRoomInput(item.getId())));
        }

        return res;
    }

    @PostMapping("rooms/delete")
   // @PreAuthorize("hasRole('ADMIN')")
    public DeleteRoomOutput deleteRoom(@RequestBody GetRoomInput input) {
        return roomService.deleteRoom(input);
    }
}
