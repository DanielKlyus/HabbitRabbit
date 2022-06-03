package com.nsu.habbitrabbit.repo;

import com.nsu.habbitrabbit.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findPlayerByName(String name);
    Player findPlayerByEmail(String email);
    Player findPlayerById(Long id);
}
