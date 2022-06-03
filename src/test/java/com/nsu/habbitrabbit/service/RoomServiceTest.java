package com.nsu.habbitrabbit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nsu.habbitrabbit.controller.dto.CreateRoomInput;
import com.nsu.habbitrabbit.controller.dto.CreateRoomOutput;
import com.nsu.habbitrabbit.controller.dto.GetRoomInput;
import com.nsu.habbitrabbit.controller.dto.GetRoomOutput;
import com.nsu.habbitrabbit.domain.Members;
import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.domain.Room;
import com.nsu.habbitrabbit.domain.visits.Visits;
import com.nsu.habbitrabbit.provider.JwtProvider;
import com.nsu.habbitrabbit.repo.ChallengeConfirmationRepository;
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
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoomService.class})
@ExtendWith(SpringExtension.class)
class RoomServiceTest {
    @MockBean
    private ChallengeConfirmationRepository challengeConfirmationRepository;

    @MockBean
    private MembersRepository membersRepository;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private PlayerService playerService;

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;


    /**
     * Method under test: {@link RoomService#createRoom(CreateRoomInput)}
     */
    @Test
    void testCreateRoom3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Iterable.iterator()" because "iterable" is null
        //       at com.nsu.habbitrabbit.service.RoomService.createRoom(RoomService.java:69)
        //   In order to prevent createRoom(CreateRoomInput)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createRoom(CreateRoomInput).
        //   See https://diff.blue/R013 to resolve this issue.

        Room room = new Room();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        room.setCreatedAt(fromResult);
        room.setCreatorId(123L);
        room.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        room.setFinishedAt(fromResult1);
        room.setId(123L);
        room.setName("Name");
        room.setRabbitsForFailure(1);
        room.setRabbitsForSuccess(1);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        room.setUpdatedAt(fromResult2);
        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.save((Room) any())).thenReturn(room);

        Members members = new Members();
        members.setPlayerId(123L);
        members.setRoomId(123L);
        MembersRepository membersRepository = mock(MembersRepository.class);
        when(membersRepository.save((Members) any())).thenReturn(members);

        Visits visits = new Visits();
        visits.setActivities(new ArrayList<>());
        visits.setId(123L);
        ChallengeConfirmationRepository challengeConfirmationRepository = mock(ChallengeConfirmationRepository.class);
        when(challengeConfirmationRepository.save((Visits) any())).thenReturn(visits);
        PlayerRepository playerRepository = mock(PlayerRepository.class);
        PlayerRepository playerRepository1 = mock(PlayerRepository.class);
        MembersRepository membersRepository1 = mock(MembersRepository.class);
        RoomRepository roomRepository1 = mock(RoomRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        RoomService roomService = new RoomService(roomRepository, membersRepository, playerRepository,
                challengeConfirmationRepository,
                new PlayerService(playerRepository1, membersRepository1, roomRepository1, passwordEncoder, new JwtProvider()));
        CreateRoomInput createRoomInput = mock(CreateRoomInput.class);
        when(createRoomInput.getRabbitsForFailure()).thenReturn(1);
        when(createRoomInput.getRabbitsForSuccess()).thenReturn(1);
        when(createRoomInput.getCreatorId()).thenReturn(123L);
        when(createRoomInput.getDescription()).thenReturn("The characteristics of someone or something");
        when(createRoomInput.getName()).thenReturn("Name");
        when(createRoomInput.getEmails()).thenReturn(new ArrayList<>());
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(createRoomInput.getFinishedAt())
                .thenReturn(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        CreateRoomOutput actualCreateRoomResult = roomService.createRoom(createRoomInput);
        assertSame(fromResult, actualCreateRoomResult.getCreatedAt());
        assertSame(fromResult2, actualCreateRoomResult.getUpdatedAt());
        assertEquals(1, actualCreateRoomResult.getRabbitsForSuccess().intValue());
        assertEquals(1, actualCreateRoomResult.getRabbitsForFailure().intValue());
        assertEquals("Name", actualCreateRoomResult.getName());
        assertEquals(123L, actualCreateRoomResult.getId().longValue());
        assertSame(fromResult1, actualCreateRoomResult.getFinishedAt());
        assertEquals("The characteristics of someone or something", actualCreateRoomResult.getDescription());
        assertEquals(123L, actualCreateRoomResult.getCreatorId().longValue());
        verify(roomRepository).save((Room) any());
        verify(membersRepository).save((Members) any());
        verify(challengeConfirmationRepository).save((Visits) any());
        verify(createRoomInput).getRabbitsForFailure();
        verify(createRoomInput).getRabbitsForSuccess();
        verify(createRoomInput, atLeast(1)).getCreatorId();
        verify(createRoomInput).getDescription();
        verify(createRoomInput).getName();
        verify(createRoomInput).getEmails();
        verify(createRoomInput).getFinishedAt();
    }

    /**
     * Method under test: {@link RoomService#createRoom(CreateRoomInput)}
     */
    @Test
    void testCreateRoom4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Iterable.iterator()" because "iterable" is null
        //       at com.nsu.habbitrabbit.service.RoomService.createRoom(RoomService.java:69)
        //   In order to prevent createRoom(CreateRoomInput)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createRoom(CreateRoomInput).
        //   See https://diff.blue/R013 to resolve this issue.

        Room room = new Room();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        room.setCreatedAt(fromResult);
        room.setCreatorId(123L);
        room.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        room.setFinishedAt(fromResult1);
        room.setId(123L);
        room.setName("Name");
        room.setRabbitsForFailure(1);
        room.setRabbitsForSuccess(1);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        room.setUpdatedAt(fromResult2);
        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.save((Room) any())).thenReturn(room);

        Members members = new Members();
        members.setPlayerId(123L);
        members.setRoomId(123L);
        MembersRepository membersRepository = mock(MembersRepository.class);
        when(membersRepository.save((Members) any())).thenReturn(members);

        Player player = new Player();
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setCreatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        player.setUpdatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        PlayerRepository playerRepository = mock(PlayerRepository.class);
        when(playerRepository.findPlayerByEmail((String) any())).thenReturn(player);

        Visits visits = new Visits();
        visits.setActivities(new ArrayList<>());
        visits.setId(123L);
        ChallengeConfirmationRepository challengeConfirmationRepository = mock(ChallengeConfirmationRepository.class);
        when(challengeConfirmationRepository.save((Visits) any())).thenReturn(visits);
        PlayerRepository playerRepository1 = mock(PlayerRepository.class);
        MembersRepository membersRepository1 = mock(MembersRepository.class);
        RoomRepository roomRepository1 = mock(RoomRepository.class);
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        RoomService roomService = new RoomService(roomRepository, membersRepository, playerRepository,
                challengeConfirmationRepository,
                new PlayerService(playerRepository1, membersRepository1, roomRepository1, passwordEncoder, new JwtProvider()));

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        CreateRoomInput createRoomInput = mock(CreateRoomInput.class);
        when(createRoomInput.getRabbitsForFailure()).thenReturn(1);
        when(createRoomInput.getRabbitsForSuccess()).thenReturn(1);
        when(createRoomInput.getCreatorId()).thenReturn(123L);
        when(createRoomInput.getDescription()).thenReturn("The characteristics of someone or something");
        when(createRoomInput.getName()).thenReturn("Name");
        when(createRoomInput.getEmails()).thenReturn(stringList);
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        when(createRoomInput.getFinishedAt())
                .thenReturn(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        CreateRoomOutput actualCreateRoomResult = roomService.createRoom(createRoomInput);
        assertSame(fromResult, actualCreateRoomResult.getCreatedAt());
        assertSame(fromResult2, actualCreateRoomResult.getUpdatedAt());
        assertEquals(1, actualCreateRoomResult.getRabbitsForSuccess().intValue());
        assertEquals(1, actualCreateRoomResult.getRabbitsForFailure().intValue());
        assertEquals("Name", actualCreateRoomResult.getName());
        assertEquals(123L, actualCreateRoomResult.getId().longValue());
        assertSame(fromResult1, actualCreateRoomResult.getFinishedAt());
        assertEquals("The characteristics of someone or something", actualCreateRoomResult.getDescription());
        assertEquals(123L, actualCreateRoomResult.getCreatorId().longValue());
        verify(roomRepository).save((Room) any());
        verify(membersRepository, atLeast(1)).save((Members) any());
        verify(playerRepository).findPlayerByEmail((String) any());
        verify(challengeConfirmationRepository).save((Visits) any());
        verify(createRoomInput).getRabbitsForFailure();
        verify(createRoomInput).getRabbitsForSuccess();
        verify(createRoomInput, atLeast(1)).getCreatorId();
        verify(createRoomInput).getDescription();
        verify(createRoomInput).getName();
        verify(createRoomInput).getEmails();
        verify(createRoomInput).getFinishedAt();
    }

    /**
     * Method under test: {@link RoomService#getRoom(GetRoomInput)}
     */
    @Test
    void testGetRoom() {
        Room room = new Room();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        room.setCreatedAt(fromResult);
        room.setCreatorId(123L);
        room.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        room.setFinishedAt(fromResult1);
        room.setId(123L);
        room.setName("Name");
        room.setRabbitsForFailure(1);
        room.setRabbitsForSuccess(1);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult2 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        room.setUpdatedAt(fromResult2);
        when(this.roomRepository.findRoomById((Long) any())).thenReturn(room);
        when(this.membersRepository.getAllByRoomId((Long) any())).thenReturn(new ArrayList<>());
        GetRoomOutput actualRoom = this.roomService.getRoom(new GetRoomInput());
        assertSame(fromResult, actualRoom.getCreatedAt());
        assertSame(fromResult2, actualRoom.getUpdatedAt());
        assertEquals(1, actualRoom.getRabbitsForSuccess().intValue());
        assertEquals(1, actualRoom.getRabbitsForFailure().intValue());
        assertEquals("Name", actualRoom.getName());
        assertSame(fromResult1, actualRoom.getFinishedAt());
        assertEquals("The characteristics of someone or something", actualRoom.getDescription());
        assertEquals(123L, actualRoom.getCreatorId().longValue());
        verify(this.roomRepository).findRoomById((Long) any());
    }
}

