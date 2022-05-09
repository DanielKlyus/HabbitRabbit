package com.nsu.habbitrabbit.controller;

import com.nsu.habbitrabbit.controller.dto.CreateRoomInput;
import com.nsu.habbitrabbit.controller.dto.CreateRoomOutput;
import com.nsu.habbitrabbit.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){ this.roomService = roomService; }

    @PostMapping("rooms/create")
    public CreateRoomOutput createRoom(@RequestBody CreateRoomInput input){
        return roomService.createRoom(input);
    }
}
