package org.example.bootcampspringasterixapi.service;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IdServiceTest {

    @Test
    void randomIdTest() {
        // GIVEN
        String expectedId = "123e4567-e89b-12d3-a456-426614174000";
        UUID mockUUID = UUID.fromString(expectedId);
        IdService idService = new IdService();
        mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(mockUUID);

        // WHEN
        String actualId = idService.randomId();

        // THEN
        assertEquals(expectedId,actualId);
    }
}