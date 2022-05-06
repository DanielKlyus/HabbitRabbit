package com.nsu.habbitrabbit.service;

import com.nsu.habbitrabbit.controller.dto.CreatePlayerInput;
import com.nsu.habbitrabbit.controller.dto.CreatePlayerOutput;
import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.repo.PlayerRepository;
import com.nsu.habbitrabbit.service.mapper.player.CreatePlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlayerService {
    PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public CreatePlayerOutput createPlayer(CreatePlayerInput input) {
        Player current = playerRepository.findPlayerByEmail(input.getEmail());

        if (current != null) {
            return new CreatePlayerOutput("Такой пользователь уже существует!");
        }

        current = new Player();
        current.setEmail(input.getEmail());
        current.setName(input.getName());
        current.setPassword(input.getPassword()); /// засунуть инкодер
        current.setActive(true);
        current.setCreatedAt(new Date());
        current.setUpdatedAt(new Date());

        current = playerRepository.save(current);

        return CreatePlayerMapper.mapPlayerToDTO(current);
    }
}

