package com.nsu.habbitrabbit.service.mapper.player;

import com.nsu.habbitrabbit.controller.dto.CreatePlayerOutput;
import com.nsu.habbitrabbit.domain.Player;

public class CreatePlayerMapper {
    public static CreatePlayerOutput mapPlayerToDTO(Player player, String jwt) {
        return new CreatePlayerOutput(
                player.getId(),
                player.getName(),
                player.getEmail(),
                player.isActive(),
                player.getUpdatedAt(),
                player.getCreatedAt(),
                jwt
        );
    }
}
