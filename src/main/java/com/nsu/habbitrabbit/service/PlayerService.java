package com.nsu.habbitrabbit.service;

import com.nsu.habbitrabbit.controller.dto.*;
import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.provider.JwtProvider;
import com.nsu.habbitrabbit.repo.PlayerRepository;
import com.nsu.habbitrabbit.service.mapper.player.ChangePlayerMapper;
import com.nsu.habbitrabbit.service.mapper.player.CreatePlayerMapper;
import com.nsu.habbitrabbit.service.mapper.player.GetCountOfRabbitsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.nsu.habbitrabbit.domain.Credentials;


import java.util.Date;

@Service
public class PlayerService {
    PlayerRepository playerRepository;
    PasswordEncoder passwordEncoder;
    JwtProvider jwtProvider;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public UserAuthOutput authPlayer(UserAuthInput input) {
        Player player = playerRepository.findPlayerByEmail(input.getEmail());

        if (player != null) {
            if (passwordEncoder.matches(input.getPassword(), player.getPassword())) {
                return new UserAuthOutput(
                        player.getName(),
                        player.getEmail(),
                        jwtProvider.generateToken(player.getEmail(),
                                15,
                                JwtProvider.jwtSecret)
                );
            }
        }
        return null;
    }

    public CreatePlayerOutput createPlayer(CreatePlayerInput input) {
        Player current = playerRepository.findPlayerByEmail(input.getEmail());

        if (current != null) {
            return new CreatePlayerOutput("Такой пользователь уже существует!");
        }

        current = new Player();
        current.setEmail(input.getEmail());
        current.setName(input.getName());
        current.setPassword(passwordEncoder.encode(input.getPassword()));
        current.setActive(true);
        current.setCreatedAt(new Date());
        current.setUpdatedAt(new Date());
        current.setCountOfRabbits(0);

        current = playerRepository.save(current);

        return CreatePlayerMapper.mapPlayerToDTO(current, jwtProvider.generateToken(
                current.getEmail(),
                15,
                JwtProvider.jwtSecret));
    }

    public ChangePlayerOutput changePlayer(ChangePlayerInput input) {
        Credentials cred = (Credentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var current = playerRepository.findPlayerByEmail(cred.getUsername());

        if (current == null) {
            return new ChangePlayerOutput("Пользователя с таким email не существует");
        }

        current.setName(input.getName());
        current.setPassword(passwordEncoder.encode(input.getPassword()));
        Date nowDate = new Date();
        current.setUpdatedAt(nowDate);
        current = playerRepository.save(current);

        return ChangePlayerMapper.mapPlayerToDTO(current);
    }

    public GetRabbitsOutput getCountOfRabbits(GetRabbitsInput input) {
        Long playerId = input.getPlayerID();
        Player current = playerRepository.findPlayerById(playerId);

        return GetCountOfRabbitsMapper.mapRabbitsToDTO(current.getCountOfRabbits());
    }

    public GetRabbitsOutput addCountOfRabbits(AddRabbitsInput input) {
        Player current = addRabbits(input.getPlayerID(), input.getRabbitsToAdd());
        current = playerRepository.save(current);
        return GetCountOfRabbitsMapper.mapRabbitsToDTO(current.getCountOfRabbits());
    }

    public Player addRabbits(Long playerId, int rabbitsToAdd) {
        Player current = playerRepository.findPlayerById(playerId);

        Integer currentCountOfRabbits = current.getCountOfRabbits();
        currentCountOfRabbits = currentCountOfRabbits + rabbitsToAdd;
        current.setCountOfRabbits(currentCountOfRabbits);

        Date nowDate = new Date();
        current.setUpdatedAt(nowDate);
        return current;
    }

    public GetRabbitsOutput removeCountOfRabbits(RemoveRabbitsInput input) {
        Player current = removeRabbits(input.getPlayerID(), input.getRabbitsToRemove());
        current = playerRepository.save(current);

        return GetCountOfRabbitsMapper.mapRabbitsToDTO(current.getCountOfRabbits());
    }

    public Player removeRabbits(Long playerId, int rabbitsToRemove) {
        Player current = playerRepository.findPlayerById(playerId);

        Integer currentCountOfRabbits = current.getCountOfRabbits();
        currentCountOfRabbits = currentCountOfRabbits - rabbitsToRemove;
        if (currentCountOfRabbits < 0) {
            currentCountOfRabbits = 0;
        }
        current.setCountOfRabbits(currentCountOfRabbits);

        Date nowDate = new Date();
        current.setUpdatedAt(nowDate);

        return current;
    }


}

