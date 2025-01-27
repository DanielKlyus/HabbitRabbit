package com.nsu.habbitrabbit.service;

import com.nsu.habbitrabbit.controller.dto.*;
import com.nsu.habbitrabbit.domain.Credentials;
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
import org.springframework.security.core.context.SecurityContextHolder;
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


        var emails = input.getEmails();
        for (var email : emails) {
            var m = new Members();
            m.setRoomId(current.getId());
            var player = playerRepository.findPlayerByEmail(email);
            createYesterdayVisit(player.getId(), current.getId());
            if (player != null) {
                m.setPlayerId(player.getId());
            }

            membersRepository.save(m);
        }

        createYesterdayVisit(input.getCreatorId(), current.getId());
        return CreateRoomMapper.mapRoomToDTO(current);
    }

    private void createYesterdayVisit(Long playerId, Long roomId) {
        Visits visits = new Visits();
        visits.setId(playerId);
        var list = new ArrayList<RoomActivity>();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        var yesterday = cal.getTime();

        list.add(new RoomActivity(roomId, 0, yesterday, true));
        visits.setActivities(list);
        challengeConfirmationRepository.save(visits);
        membersRepository.save(new Members(roomId, playerId));
    }

    public GetRoomOutput getRoom(GetRoomInput input) {
        var room = roomRepository.findRoomById(input.getRoomId());
        var isFinished = room.getFinishedAt().before(new Date());
        var isClicked = false;
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
        Credentials cred = (Credentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var currentPlayer = playerRepository.findPlayerByEmail(cred.getEmail());
        var members = membersRepository.getAllByRoomId(room.getId());
        var playersIds = members.stream().map(Members::getPlayerId).collect(Collectors.toList());
        for (Long playerId : playersIds) {
            if(Objects.equals(playerId, currentPlayer.getId())) {
                var visits = challengeConfirmationRepository.findVisitsByPlayerId(playerId);
                var activities = visits.getActivities();
                for(RoomActivity activity : activities) {
                    if(Objects.equals(activity.getRoomId(), room.getId())) {
                        isClicked = activity.getLastVisitAt().getDay() == new Date().getDay();
                    }
                }

            }
        }
        return GetRoomMapper.mapRoomToDTO(room, isFinished, isClicked);
    }

    public DeleteRoomOutput deleteRoom(GetRoomInput input) {
        var roomMembers = membersRepository.getAllByRoomId(input.getRoomId());
        membersRepository.deleteAllByRoomId(input.getRoomId());
        for(Members member : roomMembers) {
            var visits = challengeConfirmationRepository.findVisitsByPlayerId(member.getPlayerId());
            if(visits != null) {
                var currentActivities = visits.getActivities();
                int i = 0;
                for (RoomActivity activity : currentActivities) {
                    if (Objects.equals(activity.getRoomId(), input.getRoomId())) {
                        break;
                    }
                    i++;
                }
                currentActivities.remove(i);
                var newVisits = new Visits();
                newVisits.setActivities(currentActivities);
                newVisits.setId(visits.getId());
                challengeConfirmationRepository.save(newVisits);
            }
        }
        roomRepository.deleteRoomById(input.getRoomId());
        return new DeleteRoomOutput(true, null);
    }

    public ArrayList<Room> getRooms(Long playerId) {
        var roomsIds = membersRepository.getAllByPlayerId(playerId);
        if (roomsIds == null) {
            return null;
        }

        var ids = new ArrayList<Long>();
        for (var item : roomsIds) {
            ids.add(item.getRoomId());
        }

        return roomRepository.findAllByIdIn(ids);
    }
}


