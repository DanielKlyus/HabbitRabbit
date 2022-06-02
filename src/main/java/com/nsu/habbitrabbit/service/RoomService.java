package com.nsu.habbitrabbit.service;

import com.nsu.habbitrabbit.controller.dto.CreateRoomInput;
import com.nsu.habbitrabbit.controller.dto.CreateRoomOutput;
import com.nsu.habbitrabbit.controller.dto.GetRoomInput;
import com.nsu.habbitrabbit.controller.dto.GetRoomOutput;
import com.nsu.habbitrabbit.domain.Members;
import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.domain.Room;
import com.nsu.habbitrabbit.repo.ChallengeConfirmationRepository;
import com.nsu.habbitrabbit.repo.MembersRepository;
import com.nsu.habbitrabbit.repo.PlayerRepository;
import com.nsu.habbitrabbit.domain.visits.RoomActivity;
import com.nsu.habbitrabbit.domain.visits.Visits;
import com.nsu.habbitrabbit.repo.ChallengeConfirmationRepository;
import com.nsu.habbitrabbit.repo.RoomRepository;
import com.nsu.habbitrabbit.service.mapper.room.CreateRoomMapper;
import com.nsu.habbitrabbit.service.mapper.room.GetRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RoomService {
    RoomRepository roomRepository;
    MembersRepository membersRepository;
    PlayerRepository playerRepository;
    ChallengeConfirmationRepository challengeConfirmationRepository;

    PlayerService playerService;

    @Autowired
    public RoomService(
            RoomRepository roomRepository,
            MembersRepository membersRepository,
            PlayerRepository playerRepository,
            ChallengeConfirmationRepository challengeConfirmationRepository,
            PlayerService playerService
    ) {
        this.membersRepository = membersRepository;
        this.roomRepository = roomRepository;
        this.playerRepository = playerRepository;
        this.challengeConfirmationRepository = challengeConfirmationRepository;
        this.playerService = playerService;
    }

    public CreateRoomOutput createRoom(CreateRoomInput input) {
        Room current = new Room();
        current.setName(input.getName());
        current.setDescription(input.getDescription());
        current.setCreatorId(input.getCreatorId());
        current.setCreatedAt(new Date());
        current.setUpdatedAt(new Date());
        current.setFinishedAt(input.getFinishedAt());
        current.setRabbitsForFailure(input.getRabbitsForFailure());
        current.setRabbitsForSuccess(input.getRabbitsForSuccess());

        current = roomRepository.save(current);
        Visits visits = new Visits();
        visits.setId(input.getCreatorId());
        var list = new ArrayList<RoomActivity>();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        var yesterday = cal.getTime();

        list.add(new RoomActivity(current.getId(), 0, yesterday, true));
        visits.setActivities(list);
        challengeConfirmationRepository.save(visits);
        membersRepository.save(new Members(current.getId(), input.getCreatorId()));
        return CreateRoomMapper.mapRoomToDTO(current);
    }

    public GetRoomOutput getRoom(GetRoomInput input) {
        var room = roomRepository.findRoomById(input.getRoomId());
        var isFinished = room.getFinishedAt().before(new Date());
        if (isFinished) {
            var members = membersRepository.getAllByRoomId(room.getId());
            var playersIds = members.stream().map(Members::getPlayerId).collect(Collectors.toList());
            for (Long playerId : playersIds) {
                var visits = challengeConfirmationRepository.findVisitsByPlayerId(playerId);
                var activities = visits.getActivities();
                var currentActivity = activities.stream().filter(roomActivity ->
                        Objects.equals(roomActivity.getRoomId(), room.getId())
                ).collect(Collectors.toList()).get(0);
                var count = currentActivity.getCount();
                var realCount = ChronoUnit.DAYS.between(
                        room.getFinishedAt().toInstant(),
                        room.getCreatedAt().toInstant()) + 1;
                if (count != realCount) {
                    playerService.removeRabbits(playerId, room.getRabbitsForFailure());
                } else {
                    playerService.addRabbits(playerId, room.getRabbitsForSuccess());
                }
            }
        }
        return GetRoomMapper.mapRoomToDTO(room, isFinished);
    }
}


