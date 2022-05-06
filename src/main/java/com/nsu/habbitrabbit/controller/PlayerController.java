package com.nsu.habbitrabbit.controller;

import com.nsu.habbitrabbit.controller.dto.CreatePlayerInput;
import com.nsu.habbitrabbit.controller.dto.CreatePlayerOutput;
import com.nsu.habbitrabbit.controller.dto.UserAuthOutput;
import com.nsu.habbitrabbit.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import com.nsu.habbitrabbit.controller.dto.UserAuthInput;
import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.provider.JwtProvider;
import com.nsu.habbitrabbit.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    private PlayerService playerService;
    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @Autowired
    public PlayerController(PlayerService playerService, AuthService authService, JwtProvider jwtProvider) {
        this.playerService = playerService;
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("players/register")
    public CreatePlayerOutput createPlayer(@RequestBody CreatePlayerInput input) {
        return playerService.createPlayer(input);
    }

    @PostMapping("/auth")
    public UserAuthOutput auth(@RequestBody UserAuthInput userAuthInput) {
        Player player = authService.findByEmailAndPassword(userAuthInput.getEmail(), userAuthInput.getPassword());
        if(player == null) {
            return new UserAuthOutput(null, "Неверные данные пользователя");
        }
        return new UserAuthOutput(jwtProvider.generateToken(player.getEmail(), 15, "SERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDOR"));
    }

}
