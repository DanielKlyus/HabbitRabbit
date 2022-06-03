package com.nsu.habbitrabbit.repo;

import com.nsu.habbitrabbit.domain.visits.Visits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeConfirmationRepository extends JpaRepository<Visits, Long> {
    public Visits findVisitsByPlayerId(Long id);
}
