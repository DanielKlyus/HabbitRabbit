package com.nsu.habbitrabbit.service;

import com.nsu.habbitrabbit.controller.dto.CreateRoomInput;
import com.nsu.habbitrabbit.controller.dto.CreateRoomOutput;
import com.nsu.habbitrabbit.domain.Room;
import com.nsu.habbitrabbit.domain.visits.RoomActivity;
import com.nsu.habbitrabbit.domain.visits.Visits;
import com.nsu.habbitrabbit.repo.ChallengeConfirmationRepository;
import com.nsu.habbitrabbit.repo.RoomRepository;
import com.nsu.habbitrabbit.service.mapper.room.CreateRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Service
public class RoomService {
    RoomRepository roomRepository;
    ChallengeConfirmationRepository challengeConfirmationRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, ChallengeConfirmationRepository challengeConfirmationRepository) {
        this.roomRepository = roomRepository;
        this.challengeConfirmationRepository = challengeConfirmationRepository;
    }

    public CreateRoomOutput createRoom(CreateRoomInput input){
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
        return CreateRoomMapper.mapRoomToDTO(current);
    }
}


