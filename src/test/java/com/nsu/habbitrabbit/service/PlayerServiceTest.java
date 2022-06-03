package com.nsu.habbitrabbit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nsu.habbitrabbit.controller.dto.AddRabbitsInput;
import com.nsu.habbitrabbit.controller.dto.ChangePlayerInput;
import com.nsu.habbitrabbit.controller.dto.CreatePlayerInput;
import com.nsu.habbitrabbit.controller.dto.GetPlayerAllRoomsInput;
import com.nsu.habbitrabbit.controller.dto.GetRabbitsInput;
import com.nsu.habbitrabbit.controller.dto.RemoveRabbitsInput;
import com.nsu.habbitrabbit.controller.dto.UserAuthInput;
import com.nsu.habbitrabbit.controller.dto.UserAuthOutput;
import com.nsu.habbitrabbit.domain.Members;
import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.domain.Room;
import com.nsu.habbitrabbit.provider.JwtProvider;
import com.nsu.habbitrabbit.repo.MembersRepository;
import com.nsu.habbitrabbit.repo.PlayerRepository;
import com.nsu.habbitrabbit.repo.RoomRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PlayerService.class})
@ExtendWith(SpringExtension.class)
class PlayerServiceTest {
    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private MembersRepository membersRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerService playerService;

    @MockBean
    private RoomRepository roomRepository;

    /**
     * Method under test: {@link PlayerService#authPlayer(UserAuthInput)}
     */
    @Test
    void testAuthPlayer() {
        Player player = new Player();
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.playerRepository.findPlayerByEmail((String) any())).thenReturn(player);
        when(this.passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(true);
        when(this.jwtProvider.generateToken((Player) any(), anyInt(), (String) any())).thenReturn("ABC123");

        UserAuthInput userAuthInput = new UserAuthInput();
        userAuthInput.setEmail("jane.doe@example.org");
        userAuthInput.setPassword("iloveyou");
        UserAuthOutput actualAuthPlayerResult = this.playerService.authPlayer(userAuthInput);
        assertEquals("jane.doe@example.org", actualAuthPlayerResult.getEmail());
        assertEquals("Name", actualAuthPlayerResult.getName());
        assertEquals("ABC123", actualAuthPlayerResult.getJwt());
        assertNull(actualAuthPlayerResult.getError());
        verify(this.playerRepository).findPlayerByEmail((String) any());
        verify(this.passwordEncoder).matches((CharSequence) any(), (String) any());
        verify(this.jwtProvider).generateToken((Player) any(), anyInt(), (String) any());
    }

    /**
     * Method under test: {@link PlayerService#authPlayer(UserAuthInput)}
     */
    @Test
    void testAuthPlayer2() {
        Player player = new Player();
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.playerRepository.findPlayerByEmail((String) any())).thenReturn(player);
        when(this.passwordEncoder.matches((CharSequence) any(), (String) any())).thenReturn(false);
        when(this.jwtProvider.generateToken((Player) any(), anyInt(), (String) any())).thenReturn("ABC123");

        UserAuthInput userAuthInput = new UserAuthInput();
        userAuthInput.setEmail("jane.doe@example.org");
        userAuthInput.setPassword("iloveyou");
        assertNull(this.playerService.authPlayer(userAuthInput));
        verify(this.playerRepository).findPlayerByEmail((String) any());
        verify(this.passwordEncoder).matches((CharSequence) any(), (String) any());
    }

    /**
     * Method under test: {@link PlayerService#createPlayer(CreatePlayerInput)}
     */
    @Test
    void testCreatePlayer() {
        Player player = new Player();
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.playerRepository.findPlayerByEmail((String) any())).thenReturn(player);
        assertEquals("Такой пользователь уже существует!",
                this.playerService.createPlayer(new CreatePlayerInput()).getError());
        verify(this.playerRepository).findPlayerByEmail((String) any());
    }


    /**
     * Method under test: {@link PlayerService#getCountOfRabbits(GetRabbitsInput)}
     */
    @Test
    void testGetCountOfRabbits() {
        Player player = new Player();
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.playerRepository.findPlayerById((Long) any())).thenReturn(player);
        assertEquals(3, this.playerService.getCountOfRabbits(new GetRabbitsInput()).getCountOfRabbits().intValue());
        verify(this.playerRepository).findPlayerById((Long) any());
    }


    /**
     * Method under test: {@link PlayerService#addCountOfRabbits(AddRabbitsInput)}
     */
    @Test
    void testAddCountOfRabbits2() {
        Player player = new Player();
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Player player1 = new Player();
        player1.setActive(true);
        player1.setAdmin(true);
        player1.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        player1.setEmail("jane.doe@example.org");
        player1.setId(123L);
        player1.setName("Name");
        player1.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.playerRepository.save((Player) any())).thenReturn(player1);
        when(this.playerRepository.findPlayerById((Long) any())).thenReturn(player);

        AddRabbitsInput addRabbitsInput = new AddRabbitsInput();
        addRabbitsInput.setRabbitsToAdd(1);
        assertEquals(3, this.playerService.addCountOfRabbits(addRabbitsInput).getCountOfRabbits().intValue());
        verify(this.playerRepository).findPlayerById((Long) any());
        verify(this.playerRepository).save((Player) any());
    }

    /**
     * Method under test: {@link PlayerService#addRabbits(Long, int)}
     */
    @Test
    void testAddRabbits() {
        Player player = new Player();
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.playerRepository.findPlayerById((Long) any())).thenReturn(player);
        Player actualAddRabbitsResult = this.playerService.addRabbits(123L, 2);
        assertSame(player, actualAddRabbitsResult);
        assertEquals(5, actualAddRabbitsResult.getCountOfRabbits().intValue());
        verify(this.playerRepository).findPlayerById((Long) any());
    }


    /**
     * Method under test: {@link PlayerService#removeCountOfRabbits(RemoveRabbitsInput)}
     */
    @Test
    void testRemoveCountOfRabbits2() {
        Player player = new Player();
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Player player1 = new Player();
        player1.setActive(true);
        player1.setAdmin(true);
        player1.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        player1.setEmail("jane.doe@example.org");
        player1.setId(123L);
        player1.setName("Name");
        player1.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.playerRepository.save((Player) any())).thenReturn(player1);
        when(this.playerRepository.findPlayerById((Long) any())).thenReturn(player);

        RemoveRabbitsInput removeRabbitsInput = new RemoveRabbitsInput();
        removeRabbitsInput.setRabbitsToRemove(1);
        assertEquals(3, this.playerService.removeCountOfRabbits(removeRabbitsInput).getCountOfRabbits().intValue());
        verify(this.playerRepository).findPlayerById((Long) any());
        verify(this.playerRepository).save((Player) any());
    }

    /**
     * Method under test: {@link PlayerService#removeCountOfRabbits(RemoveRabbitsInput)}
     */
    @Test
    void testRemoveCountOfRabbits3() {
        Player player = mock(Player.class);
        when(player.getCountOfRabbits()).thenReturn(0);
        doNothing().when(player).setActive(anyBoolean());
        doNothing().when(player).setAdmin((Boolean) any());
        doNothing().when(player).setCountOfRabbits((Integer) any());
        doNothing().when(player).setCreatedAt((Date) any());
        doNothing().when(player).setEmail((String) any());
        doNothing().when(player).setId((Long) any());
        doNothing().when(player).setName((String) any());
        doNothing().when(player).setPassword((String) any());
        doNothing().when(player).setUpdatedAt((Date) any());
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        Player player1 = new Player();
        player1.setActive(true);
        player1.setAdmin(true);
        player1.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player1.setCreatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        player1.setEmail("jane.doe@example.org");
        player1.setId(123L);
        player1.setName("Name");
        player1.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player1.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.playerRepository.save((Player) any())).thenReturn(player1);
        when(this.playerRepository.findPlayerById((Long) any())).thenReturn(player);

        RemoveRabbitsInput removeRabbitsInput = new RemoveRabbitsInput();
        removeRabbitsInput.setRabbitsToRemove(1);
        assertEquals(3, this.playerService.removeCountOfRabbits(removeRabbitsInput).getCountOfRabbits().intValue());
        verify(this.playerRepository).findPlayerById((Long) any());
        verify(this.playerRepository).save((Player) any());
        verify(player).getCountOfRabbits();
        verify(player).setActive(anyBoolean());
        verify(player).setAdmin((Boolean) any());
        verify(player, atLeast(1)).setCountOfRabbits((Integer) any());
        verify(player).setCreatedAt((Date) any());
        verify(player).setEmail((String) any());
        verify(player).setId((Long) any());
        verify(player).setName((String) any());
        verify(player).setPassword((String) any());
        verify(player, atLeast(1)).setUpdatedAt((Date) any());
    }

    /**
     * Method under test: {@link PlayerService#removeRabbits(Long, int)}
     */
    @Test
    void testRemoveRabbits() {
        Player player = new Player();
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.playerRepository.findPlayerById((Long) any())).thenReturn(player);
        Player actualRemoveRabbitsResult = this.playerService.removeRabbits(123L, 1);
        assertSame(player, actualRemoveRabbitsResult);
        assertEquals(2, actualRemoveRabbitsResult.getCountOfRabbits().intValue());
        verify(this.playerRepository).findPlayerById((Long) any());
    }

    /**
     * Method under test: {@link PlayerService#removeRabbits(Long, int)}
     */
    @Test
    void testRemoveRabbits2() {
        Player player = mock(Player.class);
        when(player.getCountOfRabbits()).thenReturn(0);
        doNothing().when(player).setActive(anyBoolean());
        doNothing().when(player).setAdmin((Boolean) any());
        doNothing().when(player).setCountOfRabbits((Integer) any());
        doNothing().when(player).setCreatedAt((Date) any());
        doNothing().when(player).setEmail((String) any());
        doNothing().when(player).setId((Long) any());
        doNothing().when(player).setName((String) any());
        doNothing().when(player).setPassword((String) any());
        doNothing().when(player).setUpdatedAt((Date) any());
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.playerRepository.findPlayerById((Long) any())).thenReturn(player);
        this.playerService.removeRabbits(123L, 1);
        verify(this.playerRepository).findPlayerById((Long) any());
        verify(player).getCountOfRabbits();
        verify(player).setActive(anyBoolean());
        verify(player).setAdmin((Boolean) any());
        verify(player, atLeast(1)).setCountOfRabbits((Integer) any());
        verify(player).setCreatedAt((Date) any());
        verify(player).setEmail((String) any());
        verify(player).setId((Long) any());
        verify(player).setName((String) any());
        verify(player).setPassword((String) any());
        verify(player, atLeast(1)).setUpdatedAt((Date) any());
    }

    /**
     * Method under test: {@link PlayerService#getAllPlayerRooms(GetPlayerAllRoomsInput)}
     */
    @Test
    void testGetAllPlayerRooms() {
        when(this.membersRepository.getAllByPlayerId((Long) any())).thenReturn(new ArrayList<>());
        assertTrue(this.playerService.getAllPlayerRooms(new GetPlayerAllRoomsInput()).isEmpty());
        verify(this.membersRepository).getAllByPlayerId((Long) any());
    }

    /**
     * Method under test: {@link PlayerService#getAllPlayerRooms(GetPlayerAllRoomsInput)}
     */
    @Test
    void testGetAllPlayerRooms2() {
        Room room = new Room();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        room.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        room.setCreatorId(123L);
        room.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        room.setFinishedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        room.setId(123L);
        room.setName("Name");
        room.setRabbitsForFailure(1);
        room.setRabbitsForSuccess(1);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        room.setUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.roomRepository.findRoomById((Long) any())).thenReturn(room);

        Members members = new Members();
        members.setPlayerId(123L);
        members.setRoomId(123L);

        ArrayList<Members> membersList = new ArrayList<>();
        membersList.add(members);
        when(this.membersRepository.getAllByPlayerId((Long) any())).thenReturn(membersList);
        assertEquals(1, this.playerService.getAllPlayerRooms(new GetPlayerAllRoomsInput()).size());
        verify(this.roomRepository).findRoomById((Long) any());
        verify(this.membersRepository).getAllByPlayerId((Long) any());
    }

    /**
     * Method under test: {@link PlayerService#getAllPlayerRooms(GetPlayerAllRoomsInput)}
     */
    @Test
    void testGetAllPlayerRooms3() {
        Room room = new Room();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        room.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        room.setCreatorId(123L);
        room.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        room.setFinishedAt(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        room.setId(123L);
        room.setName("Name");
        room.setRabbitsForFailure(1);
        room.setRabbitsForSuccess(1);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        room.setUpdatedAt(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.roomRepository.findRoomById((Long) any())).thenReturn(room);

        Members members = new Members();
        members.setPlayerId(123L);
        members.setRoomId(123L);

        Members members1 = new Members();
        members1.setPlayerId(123L);
        members1.setRoomId(123L);

        ArrayList<Members> membersList = new ArrayList<>();
        membersList.add(members1);
        membersList.add(members);
        when(this.membersRepository.getAllByPlayerId((Long) any())).thenReturn(membersList);
        assertEquals(2, this.playerService.getAllPlayerRooms(new GetPlayerAllRoomsInput()).size());
        verify(this.roomRepository, atLeast(1)).findRoomById((Long) any());
        verify(this.membersRepository).getAllByPlayerId((Long) any());
    }

    /**
     * Method under test: {@link PlayerService#getAllPlayers()}
     */
    @Test
    void testGetAllPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();
        when(this.playerRepository.findAll()).thenReturn(playerList);
        ArrayList<Player> actualAllPlayers = this.playerService.getAllPlayers();
        assertSame(playerList, actualAllPlayers);
        assertTrue(actualAllPlayers.isEmpty());
        verify(this.playerRepository).findAll();
    }
}

