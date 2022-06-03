package com.nsu.habbitrabbit.controller;

import com.nsu.habbitrabbit.controller.dto.ChallengeConfirmationInput;
import com.nsu.habbitrabbit.controller.dto.ChallengeConfirmationOutput;
import com.nsu.habbitrabbit.controller.dto.GetRoomInput;
import com.nsu.habbitrabbit.controller.dto.GetRoomOutput;
import com.nsu.habbitrabbit.service.ChallengeConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChallengeConfirmationController {
    private ChallengeConfirmationService challengeConfirmationService;

    @Autowired
    public ChallengeConfirmationController(ChallengeConfirmationService challengeConfirmationService){
        this.challengeConfirmationService = challengeConfirmationService;
    }

    @PostMapping("/confirmation")
    public ChallengeConfirmationOutput challengeConfirmationOutput(@RequestBody ChallengeConfirmationInput input){
        return challengeConfirmationService.challengeConfirmationOutput(input);
    }
}
