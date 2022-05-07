package com.nsu.habbitrabbit.controller;


import com.nsu.habbitrabbit.controller.dto.CreatePlayerInput;
import com.nsu.habbitrabbit.controller.dto.CreatePlayerOutput;
import com.nsu.habbitrabbit.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("players/register")
    public CreatePlayerOutput createPlayer(@RequestBody CreatePlayerInput input) {
        return playerService.createPlayer(input);
    }
}
