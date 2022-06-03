package com.nsu.habbitrabbit.service;

import com.nsu.habbitrabbit.controller.dto.ChallengeConfirmationInput;
import com.nsu.habbitrabbit.controller.dto.ChallengeConfirmationOutput;
import com.nsu.habbitrabbit.domain.visits.RoomActivity;
import com.nsu.habbitrabbit.domain.visits.Visits;
import com.nsu.habbitrabbit.repo.ChallengeConfirmationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

@Service
public class ChallengeConfirmationService {
    ChallengeConfirmationRepository challengeConfirmationRepository;

    @Autowired
    public ChallengeConfirmationService(ChallengeConfirmationRepository challengeConfirmationRepository) {
        this.challengeConfirmationRepository = challengeConfirmationRepository;
    }

    public ChallengeConfirmationOutput challengeConfirmationOutput(ChallengeConfirmationInput input) {
        Visits current = challengeConfirmationRepository.findVisitsByPlayerId(input.getPlayerId());

        var activities = current.getActivities();

        for (RoomActivity roomActivity : activities) {
            if (roomActivity.getRoomId().equals(input.getRoomId()) && roomActivity.getLastVisitAt().getDay() != new Date().getDay()) {
                roomActivity.setCount(roomActivity.getCount() + 1);
                roomActivity.setLastVisitAt(new Date());
            }
        }
        current.setActivities(activities);
        challengeConfirmationRepository.save(current);
        return new ChallengeConfirmationOutput(true);
    }
}
