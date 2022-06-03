package com.nsu.habbitrabbit.service.mapper.player;


import com.nsu.habbitrabbit.controller.dto.GetRabbitsOutput;

public class GetCountOfRabbitsMapper {

    public static GetRabbitsOutput mapRabbitsToDTO(Integer countOfRabbits) {
        return new GetRabbitsOutput(countOfRabbits);
    }
}
