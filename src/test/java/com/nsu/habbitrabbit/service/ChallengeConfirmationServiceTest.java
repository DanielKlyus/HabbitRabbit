package com.nsu.habbitrabbit.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nsu.habbitrabbit.controller.dto.ChallengeConfirmationInput;
import com.nsu.habbitrabbit.domain.visits.Visits;
import com.nsu.habbitrabbit.repo.ChallengeConfirmationRepository;
import com.nsu.habbitrabbit.repo.PlayerRepository;
import com.nsu.habbitrabbit.repo.RoomRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class ChallengeConfirmationServiceTest {
    /**
     * Method under test: {@link ChallengeConfirmationService#challengeConfirmationOutput(ChallengeConfirmationInput)}
     */
    @Test
    void testChallengeConfirmationOutput() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.nsu.habbitrabbit.domain.visits.Visits.getActivities()" because "current" is null
        //       at com.nsu.habbitrabbit.service.ChallengeConfirmationService.challengeConfirmationOutput(ChallengeConfirmationService.java:31)
        //   In order to prevent challengeConfirmationOutput(ChallengeConfirmationInput)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   challengeConfirmationOutput(ChallengeConfirmationInput).
        //   See https://diff.blue/R013 to resolve this issue.

        Visits visits = new Visits();
        visits.setActivities(new ArrayList<>());
        visits.setId(123L);

        Visits visits1 = new Visits();
        visits1.setActivities(new ArrayList<>());
        visits1.setId(123L);
        ChallengeConfirmationRepository challengeConfirmationRepository = mock(ChallengeConfirmationRepository.class);
        when(challengeConfirmationRepository.save((Visits) any())).thenReturn(visits1);
        when(challengeConfirmationRepository.findVisitsByPlayerId((Long) any())).thenReturn(visits);
        ChallengeConfirmationService challengeConfirmationService = new ChallengeConfirmationService(
                challengeConfirmationRepository, mock(RoomRepository.class), mock(PlayerRepository.class));
        assertTrue(challengeConfirmationService.challengeConfirmationOutput(new ChallengeConfirmationInput()).isOk());
        verify(challengeConfirmationRepository).findVisitsByPlayerId((Long) any());
        verify(challengeConfirmationRepository).save((Visits) any());
    }
}

