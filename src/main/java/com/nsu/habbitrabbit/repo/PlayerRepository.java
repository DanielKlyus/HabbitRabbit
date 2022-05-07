package com.nsu.habbitrabbit.repo;

import com.nsu.habbitrabbit.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;



public interface PlayerRepository extends JpaRepository<Player, Long> {
    public Player findPlayerByName(String name);
    public Player findPlayerByEmail(String email);
}
