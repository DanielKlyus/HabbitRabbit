package com.nsu.habbitrabbit.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsu.habbitrabbit.controller.dto.CreateRoomInput;
import com.nsu.habbitrabbit.controller.dto.CreateRoomOutput;
import com.nsu.habbitrabbit.controller.dto.GetRoomInput;
import com.nsu.habbitrabbit.controller.dto.GetRoomOutput;
import com.nsu.habbitrabbit.service.RoomService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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

@ContextConfiguration(classes = {RoomController.class})
@ExtendWith(SpringExtension.class)
class RoomControllerTest {
    @Autowired
    private RoomController roomController;

    @MockBean
    private RoomService roomService;

    /**
     * Method under test: {@link RoomController#createRoom(CreateRoomInput)}
     */
    @Test
    void testCreateRoom() throws Exception {
        when(this.roomService.createRoom((CreateRoomInput) any())).thenReturn(new CreateRoomOutput("An error occurred"));

        CreateRoomInput createRoomInput = new CreateRoomInput();
        createRoomInput.setCreatorId(123L);
        createRoomInput.setDescription("The characteristics of someone or something");
        createRoomInput.setEmails(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        createRoomInput.setFinishedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        createRoomInput.setName("Name");
        createRoomInput.setRabbitsForFailure(1);
        createRoomInput.setRabbitsForSuccess(1);
        String content = (new ObjectMapper()).writeValueAsString(createRoomInput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rooms/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.roomController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"description\":null,\"creatorId\":null,\"createdAt\":null,\"updatedAt\":null,\"finishedAt"
                                        + "\":null,\"rabbitsForFailure\":null,\"rabbitsForSuccess\":null,\"error\":\"An error occurred\"}"));
    }

    /**
     * Method under test: {@link RoomController#getRoom(GetRoomInput)}
     */
    @Test
    void testGetRoom() throws Exception {
        when(this.roomService.getRoom((GetRoomInput) any())).thenReturn(new GetRoomOutput());

        GetRoomInput getRoomInput = new GetRoomInput();
        getRoomInput.setRoomId(123L);
        String content = (new ObjectMapper()).writeValueAsString(getRoomInput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rooms/get_room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.roomController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":null,\"description\":null,\"creatorId\":null,\"createdAt\":null,\"updatedAt\":null,\"finishedAt\":null"
                                        + ",\"rabbitsForFailure\":null,\"rabbitsForSuccess\":null,\"finished\":null}"));
    }
}

