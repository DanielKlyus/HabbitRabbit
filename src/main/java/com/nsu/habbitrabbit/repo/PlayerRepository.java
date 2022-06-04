package com.nsu.habbitrabbit.repo;

import com.nsu.habbitrabbit.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;


public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findPlayerByName(String name);
    Player findPlayerByEmail(String email);
    Player findPlayerById(Long id);
    ArrayList<Player> findAll();
    @Transactional
    void deletePlayerById(Long id);
}
