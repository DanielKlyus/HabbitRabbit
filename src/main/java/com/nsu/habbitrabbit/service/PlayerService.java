package com.nsu.habbitrabbit.service;

import com.nsu.habbitrabbit.controller.dto.CreatePlayerInput;
import com.nsu.habbitrabbit.controller.dto.CreatePlayerOutput;
import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public CreatePlayerOutput createPlayer(CreatePlayerInput input) {

        Player current = playerRepository.findPlayerByEmail(input.getEmail());

        if (current == null) {
            return null;
        }

        CreatePlayerOutput output = new CreatePlayerOutput(0, input.getName(), input.getEmail(),
                input.getPassword(), true, 0, 0);

        Date currentDate = new Date();
        output.setCreatedAt(currentDate.getTime());
        output.setUpdatedAt(currentDate.getTime());

        // TODO сгенерировать id

        return output;
    }


}

