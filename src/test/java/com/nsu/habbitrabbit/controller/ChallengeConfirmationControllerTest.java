package com.nsu.habbitrabbit.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsu.habbitrabbit.controller.dto.ChallengeConfirmationInput;
import com.nsu.habbitrabbit.controller.dto.ChallengeConfirmationOutput;
import com.nsu.habbitrabbit.service.ChallengeConfirmationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ChallengeConfirmationController.class})
@ExtendWith(SpringExtension.class)
class ChallengeConfirmationControllerTest {
    @Autowired
    private ChallengeConfirmationController challengeConfirmationController;

    @MockBean
    private ChallengeConfirmationService challengeConfirmationService;

    /**
     * Method under test: {@link ChallengeConfirmationController#challengeConfirmationOutput(ChallengeConfirmationInput)}
     */
    @Test
    void testChallengeConfirmationOutput() throws Exception {
        when(this.challengeConfirmationService.challengeConfirmationOutput((ChallengeConfirmationInput) any()))
                .thenReturn(new ChallengeConfirmationOutput(true));

        ChallengeConfirmationInput challengeConfirmationInput = new ChallengeConfirmationInput();
        challengeConfirmationInput.setPlayerId(123L);
        challengeConfirmationInput.setRoomId(123L);
        String content = (new ObjectMapper()).writeValueAsString(challengeConfirmationInput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/confirmation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.challengeConfirmationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"ok\":true,\"error\":null}"));
    }
}

