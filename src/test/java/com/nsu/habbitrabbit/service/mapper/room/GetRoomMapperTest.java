package com.nsu.habbitrabbit.service.mapper.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nsu.habbitrabbit.controller.dto.GetRoomOutput;
import com.nsu.habbitrabbit.domain.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class GetRoomMapperTest {
    /**
     * Method under test: {@link GetRoomMapper#mapRoomToDTO(Room, boolean)}
     */
    @Test
    void testMapRoomToDTO() {
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
        GetRoomOutput actualMapRoomToDTOResult = GetRoomMapper.mapRoomToDTO(room, true);
        assertSame(fromResult, actualMapRoomToDTOResult.getCreatedAt());
        assertSame(fromResult2, actualMapRoomToDTOResult.getUpdatedAt());
        assertEquals(1, actualMapRoomToDTOResult.getRabbitsForSuccess().intValue());
        assertEquals(1, actualMapRoomToDTOResult.getRabbitsForFailure().intValue());
        assertEquals("Name", actualMapRoomToDTOResult.getName());
        assertSame(fromResult1, actualMapRoomToDTOResult.getFinishedAt());
        assertTrue(actualMapRoomToDTOResult.getFinished());
        assertEquals("The characteristics of someone or something", actualMapRoomToDTOResult.getDescription());
        assertEquals(123L, actualMapRoomToDTOResult.getCreatorId().longValue());
    }
}

