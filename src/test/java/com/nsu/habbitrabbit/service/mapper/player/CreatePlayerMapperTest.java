package com.nsu.habbitrabbit.service.mapper.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nsu.habbitrabbit.controller.dto.CreatePlayerOutput;
import com.nsu.habbitrabbit.domain.Player;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;

class CreatePlayerMapperTest {
    /**
     * Method under test: {@link CreatePlayerMapper#mapPlayerToDTO(Player, String)}
     */
    @Test
    void testMapPlayerToDTO() {
        Player player = new Player();
        player.setActive(true);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        player.setCreatedAt(fromResult);
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        player.setUpdatedAt(fromResult1);
        CreatePlayerOutput actualMapPlayerToDTOResult = CreatePlayerMapper.mapPlayerToDTO(player, "Jwt");
        assertSame(fromResult, actualMapPlayerToDTOResult.getCreatedAt());
        assertTrue(actualMapPlayerToDTOResult.isActive());
        assertSame(fromResult1, actualMapPlayerToDTOResult.getUpdatedAt());
        assertEquals("Name", actualMapPlayerToDTOResult.getName());
        assertEquals("Jwt", actualMapPlayerToDTOResult.getJwt());
        assertEquals(123L, actualMapPlayerToDTOResult.getId().longValue());
        assertEquals("jane.doe@example.org", actualMapPlayerToDTOResult.getEmail());
    }

    /**
     * Method under test: {@link CreatePlayerMapper#mapPlayerToDTO(Player, String)}
     */
    @Test
    void testMapPlayerToDTO2() {
        Player player = new Player();
        player.setActive(false);
        player.setAdmin(true);
        player.setCountOfRabbits(3);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        player.setCreatedAt(fromResult);
        player.setEmail("jane.doe@example.org");
        player.setId(123L);
        player.setName("Name");
        player.setPassword("iloveyou");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        player.setUpdatedAt(fromResult1);
        CreatePlayerOutput actualMapPlayerToDTOResult = CreatePlayerMapper.mapPlayerToDTO(player, "Jwt");
        assertSame(fromResult, actualMapPlayerToDTOResult.getCreatedAt());
        assertFalse(actualMapPlayerToDTOResult.isActive());
        assertSame(fromResult1, actualMapPlayerToDTOResult.getUpdatedAt());
        assertEquals("Name", actualMapPlayerToDTOResult.getName());
        assertEquals("Jwt", actualMapPlayerToDTOResult.getJwt());
        assertEquals(123L, actualMapPlayerToDTOResult.getId().longValue());
        assertEquals("jane.doe@example.org", actualMapPlayerToDTOResult.getEmail());
    }
}

