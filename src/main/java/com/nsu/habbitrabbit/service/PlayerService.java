package com.nsu.habbitrabbit.service;

import com.nsu.habbitrabbit.controller.dto.*;
import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.provider.JwtProvider;
import com.nsu.habbitrabbit.repo.PlayerRepository;
import com.nsu.habbitrabbit.service.mapper.player.ChangePlayerMapper;
import com.nsu.habbitrabbit.service.mapper.player.CreatePlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                                "SERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDOR")
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

        current = playerRepository.save(current);

        return CreatePlayerMapper.mapPlayerToDTO(current, jwtProvider.generateToken(
                current.getEmail(),
                15,
                "SERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDORSERGEYPIDOR"));
    }

    public ChangePlayerOutput changePlayer(ChangePlayerInput input) {
        Player current = playerRepository.findPlayerByEmail(input.getOldEmail());

        if (current == null) {
            return new ChangePlayerOutput("Пользователя с таким email не существует");
        }

        if (!(passwordEncoder.matches(input.getOldPassword(), current.getPassword()))) {
            return new ChangePlayerOutput("Текущий пароль введён неверно!");
        }

        if (!(input.getOldEmail().equals(input.getNewEmail()))) {
            Player playerWithNewEmail = playerRepository.findPlayerByEmail(input.getNewEmail());
            if (playerWithNewEmail != null) {
                return new ChangePlayerOutput("Этот email уже зарегистрирован");
            }
        }

        current.setEmail(input.getNewEmail());
        current.setName(input.getNewName());
        current.setPassword(passwordEncoder.encode(input.getNewPassword()));
        Date nowDate = new Date();
        current.setUpdatedAt(nowDate);
        current = playerRepository.save(current);

        return ChangePlayerMapper.mapPlayerToDTO(current);
    }
}

