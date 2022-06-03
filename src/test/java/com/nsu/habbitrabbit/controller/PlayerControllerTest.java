package com.nsu.habbitrabbit.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsu.habbitrabbit.controller.dto.AddRabbitsInput;
import com.nsu.habbitrabbit.controller.dto.ChangePlayerInput;
import com.nsu.habbitrabbit.controller.dto.ChangePlayerOutput;
import com.nsu.habbitrabbit.controller.dto.CreatePlayerInput;
import com.nsu.habbitrabbit.controller.dto.CreatePlayerOutput;
import com.nsu.habbitrabbit.controller.dto.GetPlayerAllRoomsInput;
import com.nsu.habbitrabbit.controller.dto.GetRabbitsInput;
import com.nsu.habbitrabbit.controller.dto.GetRabbitsOutput;
import com.nsu.habbitrabbit.controller.dto.RemoveRabbitsInput;
import com.nsu.habbitrabbit.controller.dto.UserAuthInput;
import com.nsu.habbitrabbit.controller.dto.UserAuthOutput;
import com.nsu.habbitrabbit.service.PlayerService;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {PlayerController.class})
@ExtendWith(SpringExtension.class)
class PlayerControllerTest {
    @Autowired
    private PlayerController playerController;

    @MockBean
    private PlayerService playerService;

    /**
     * Method under test: {@link PlayerController#createPlayer(CreatePlayerInput)}
     */
    @Test
    void testCreatePlayer() throws Exception {
        when(this.playerService.createPlayer((CreatePlayerInput) any()))
                .thenReturn(new CreatePlayerOutput("An error occurred"));

        CreatePlayerInput createPlayerInput = new CreatePlayerInput();
        createPlayerInput.setEmail("jane.doe@example.org");
        createPlayerInput.setIsAdmin(true);
        createPlayerInput.setName("Name");
        createPlayerInput.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(createPlayerInput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/players/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"email\":null,\"updatedAt\":null,\"createdAt\":null,\"jwt\":null,\"error\":\"An error"
                                        + " occurred\",\"active\":false}"));
    }

    /**
     * Method under test: {@link PlayerController#addRabbits(AddRabbitsInput)}
     */
    @Test
    void testAddRabbits() throws Exception {
        when(this.playerService.addCountOfRabbits((AddRabbitsInput) any())).thenReturn(new GetRabbitsOutput());

        AddRabbitsInput addRabbitsInput = new AddRabbitsInput();
        addRabbitsInput.setPlayerID(1L);
        addRabbitsInput.setRabbitsToAdd(1);
        String content = (new ObjectMapper()).writeValueAsString(addRabbitsInput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/players/addRabbits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"countOfRabbits\":null}"));
    }

    /**
     * Method under test: {@link PlayerController#changePlayer(ChangePlayerInput)}
     */
    @Test
    void testChangePlayer() throws Exception {
        when(this.playerService.changePlayer((ChangePlayerInput) any()))
                .thenReturn(new ChangePlayerOutput("An error occurred"));

        ChangePlayerInput changePlayerInput = new ChangePlayerInput();
        changePlayerInput.setName("New Name");
        changePlayerInput.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(changePlayerInput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/players/change")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"email\":null,\"updatedAt\":null,\"createdAt\":null,\"error\":\"An error occurred\","
                                        + "\"active\":false}"));
    }

    /**
     * Method under test: {@link PlayerController#getAllPlayers()}
     */
    @Test
    void testGetAllPlayers() throws Exception {
        when(this.playerService.getAllPlayers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/getAllPlayers");
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlayerController#getAllPlayers()}
     */
    @Test
    void testGetAllPlayers2() throws Exception {
        when(this.playerService.getAllPlayers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/players/getAllPlayers");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlayerController#auth(UserAuthInput)}
     */
    @Test
    void testAuth() throws Exception {
        when(this.playerService.authPlayer((UserAuthInput) any()))
                .thenReturn(new UserAuthOutput("Jwt", "An error occurred"));

        UserAuthInput userAuthInput = new UserAuthInput();
        userAuthInput.setEmail("jane.doe@example.org");
        userAuthInput.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(userAuthInput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"name\":null,\"email\":null,\"jwt\":\"Jwt\",\"error\":\"An error occurred\"}"));
    }

    /**
     * Method under test: {@link PlayerController#getPlayerRooms(GetPlayerAllRoomsInput)}
     */
    @Test
    void testGetPlayerRooms() throws Exception {
        when(this.playerService.getAllPlayerRooms((GetPlayerAllRoomsInput) any())).thenReturn(new ArrayList<>());

        GetPlayerAllRoomsInput getPlayerAllRoomsInput = new GetPlayerAllRoomsInput();
        getPlayerAllRoomsInput.setPlayerId(123L);
        String content = (new ObjectMapper()).writeValueAsString(getPlayerAllRoomsInput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/get_user_room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PlayerController#getRabbits(GetRabbitsInput)}
     */
    @Test
    void testGetRabbits() throws Exception {
        when(this.playerService.getCountOfRabbits((GetRabbitsInput) any())).thenReturn(new GetRabbitsOutput());

        GetRabbitsInput getRabbitsInput = new GetRabbitsInput();
        getRabbitsInput.setPlayerID(1L);
        String content = (new ObjectMapper()).writeValueAsString(getRabbitsInput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/players/getRabbits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"countOfRabbits\":null}"));
    }

    /**
     * Method under test: {@link PlayerController#removeRabbits(RemoveRabbitsInput)}
     */
    @Test
    void testRemoveRabbits() throws Exception {
        when(this.playerService.removeCountOfRabbits((RemoveRabbitsInput) any())).thenReturn(new GetRabbitsOutput());

        RemoveRabbitsInput removeRabbitsInput = new RemoveRabbitsInput();
        removeRabbitsInput.setPlayerID(1L);
        removeRabbitsInput.setRabbitsToRemove(1);
        String content = (new ObjectMapper()).writeValueAsString(removeRabbitsInput);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/players/removeRabbits")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.playerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"countOfRabbits\":null}"));
    }
}

