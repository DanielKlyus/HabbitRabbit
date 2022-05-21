package com.nsu.habbitrabbit.controller;

import com.nsu.habbitrabbit.controller.dto.*;
import com.nsu.habbitrabbit.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/auth")
    public UserAuthOutput auth(@RequestBody UserAuthInput userAuthInput) {
        return playerService.authPlayer(userAuthInput);
    }

    @PostMapping("/players/change")
    public ChangePlayerOutput changePlayer(@RequestBody ChangePlayerInput input) {
        return playerService.changePlayer(input);
    }

}
