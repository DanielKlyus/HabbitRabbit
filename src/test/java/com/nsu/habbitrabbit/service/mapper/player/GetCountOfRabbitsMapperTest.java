package com.nsu.habbitrabbit.service.mapper.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GetCountOfRabbitsMapperTest {
    /**
     * Method under test: {@link GetCountOfRabbitsMapper#mapRabbitsToDTO(Integer)}
     */
    @Test
    void testMapRabbitsToDTO() {
        assertEquals(3, GetCountOfRabbitsMapper.mapRabbitsToDTO(3).getCountOfRabbits().intValue());
    }
}

