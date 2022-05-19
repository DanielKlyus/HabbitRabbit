package com.nsu.habbitrabbit.service.mapper.player;

import com.nsu.habbitrabbit.controller.dto.ChangePlayerOutput;
import com.nsu.habbitrabbit.domain.Player;

public class ChangePlayerMapper {
    public static ChangePlayerOutput mapPlayerToDTO(Player player) {
        return new ChangePlayerOutput(
                player.getId(),
                player.getName(),
                player.getEmail(),
                player.isActive(),
                player.getUpdatedAt(),
                player.getCreatedAt()
        );
    }
}
