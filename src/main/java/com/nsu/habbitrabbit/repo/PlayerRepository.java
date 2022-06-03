package com.nsu.habbitrabbit.repo;

import com.nsu.habbitrabbit.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PlayerRepository extends JpaRepository<Player, Long> {
    public Player findPlayerByName(String name);
    public Player findPlayerByEmail(String email);
    public Player findPlayerById(Long id);
}
