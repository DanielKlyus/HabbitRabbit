package com.nsu.habbitrabbit.controller;

import com.nsu.habbitrabbit.controller.dto.*;
import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.domain.Room;
import com.nsu.habbitrabbit.repo.PlayerRepository;
import com.nsu.habbitrabbit.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PlayerController {
    private PlayerService playerService;
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/players/register")
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

    @GetMapping("/players/getRabbits")
    public GetRabbitsOutput getRabbits(@RequestBody GetRabbitsInput input) {
        return playerService.getCountOfRabbits(input);
    }

    @PostMapping("/players/addRabbits")
    public GetRabbitsOutput addRabbits(@RequestBody AddRabbitsInput input) {
        return playerService.addCountOfRabbits(input);
    }

    @PostMapping("/players/removeRabbits")
    public GetRabbitsOutput removeRabbits(@RequestBody RemoveRabbitsInput input) {
        return playerService.removeCountOfRabbits(input);
    }

    @GetMapping("/players/get_user_room")
    public ArrayList<Room> getPlayerRooms(@RequestBody GetPlayerAllRoomsInput input){
        return playerService.getAllPlayerRooms(input);
    }
    @GetMapping("/players/getAllPlayers")
    public ArrayList<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }
}
