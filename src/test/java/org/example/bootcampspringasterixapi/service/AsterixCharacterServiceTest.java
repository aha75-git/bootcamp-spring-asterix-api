package org.example.bootcampspringasterixapi.service;

import org.example.bootcampspringasterixapi.dto.AsterixCharacterDto;
import org.example.bootcampspringasterixapi.model.AsterixCharacter;
import org.example.bootcampspringasterixapi.repository.CharacterRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AsterixCharacterServiceTest {

    @Test
    void getCharacters_shouldReturnAsterixCharacters_whenCalledWithValidInput() {
        // GIVEN
        AsterixCharacter asterixCharacter1 = new AsterixCharacter("1", "Asterix", 35, "Warrior");
        AsterixCharacter asterixCharacter2 = new AsterixCharacter("2", "Obelix", 33, "Warrior");
        List<AsterixCharacter> asterixCharacters = List.of(asterixCharacter1,asterixCharacter2);
        CharacterRepository mockRepository = mock(CharacterRepository.class);
        IdService mockIdService = mock(IdService.class);
        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(mockRepository, mockIdService);
        when(mockRepository.findAll()).thenReturn(asterixCharacters);

        // WHEN
        var actual = asterixCharacterService.getCharacters();

        // THEN
        assertEquals(asterixCharacters.size(),actual.size());
        verify(mockRepository).findAll();
    }

    @Test
    void getCharactersById() {
        // GIVEN
        AsterixCharacter asterixCharacter = new AsterixCharacter("1", "Asterix", 35, "Warrior");
        CharacterRepository mockRepository = mock(CharacterRepository.class);
        IdService mockIdService = mock(IdService.class);
        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(mockRepository, mockIdService);
        Optional<AsterixCharacter> response = Optional.of(asterixCharacter);
        when(mockRepository.findById(asterixCharacter.id())).thenReturn(response);

        // WHEN
        var actual = asterixCharacterService.getCharactersById(asterixCharacter.id());

        // THEN
        assertEquals(asterixCharacter,actual);
        verify(mockRepository).findById(asterixCharacter.id());
    }

    @Test
    void addCharacter() {
        // GIVEN
        AsterixCharacter asterixCharacter = new AsterixCharacter("1", "Asterix", 35, "Warrior");
        AsterixCharacterDto asterixCharacterDto = new AsterixCharacterDto("Asterix", 35, "Warrior");
        CharacterRepository mockRepository = mock(CharacterRepository.class);
        IdService mockIdService = mock(IdService.class);
        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(mockRepository, mockIdService);
        when(mockRepository.save(any(AsterixCharacter.class))).thenReturn(asterixCharacter);
        when(mockIdService.randomId()).thenReturn(asterixCharacter.id());

        // WHEN
        AsterixCharacter actual = asterixCharacterService.addCharacter(asterixCharacterDto);

        // THEN
        assertEquals(asterixCharacter,actual);
        verify(mockIdService).randomId();
        verify(mockRepository).save(any(AsterixCharacter.class));
    }

    @Test
    void deleteCharacter() {
        // GIVEN
        AsterixCharacter asterixCharacter = new AsterixCharacter("1", "Asterix", 35, "Warrior");
        CharacterRepository mockRepository = mock(CharacterRepository.class);
        IdService mockIdService = mock(IdService.class);
        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(mockRepository, mockIdService);

        // WHEN
        asterixCharacterService.deleteCharacter(asterixCharacter.id());

        // THEN
        verify(mockRepository).deleteById(asterixCharacter.id());
    }

    @Test
    void updateCharacter() {
        // GIVEN
        String id = "1";
        AsterixCharacter asterixCharacterExist = new AsterixCharacter(id, "AsterixOld", 22, "Warrior Old");
        AsterixCharacter asterixCharacterUpdated = new AsterixCharacter(id, "Asterix", 35, "Warrior");
        AsterixCharacterDto asterixCharacterDto = new AsterixCharacterDto("Asterix", 35, "Warrior");
        CharacterRepository mockRepository = mock(CharacterRepository.class);
        IdService mockIdService = mock(IdService.class);
        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(mockRepository, mockIdService);
        when(mockIdService.randomId()).thenReturn(id);
        when(mockRepository.findById(id)).thenReturn(Optional.of(asterixCharacterExist));
        when(mockRepository.save(any(AsterixCharacter.class))).thenReturn(asterixCharacterUpdated);

        // WHEN
        AsterixCharacter actual = asterixCharacterService.updateCharacter(id, asterixCharacterDto);

        // THEN
        assertEquals(asterixCharacterUpdated,actual);
        verify(mockRepository).findById(id);
        verify(mockRepository).save(any(AsterixCharacter.class));
    }

    @Test
    void getCharactersByProfession() {
        // GIVEN
        AsterixCharacter asterixCharacter1 = new AsterixCharacter("1", "Asterix", 35, "Warrior");
        AsterixCharacter asterixCharacter2 = new AsterixCharacter("2", "Obelix", 33, "Supplier");
        List<AsterixCharacter> asterixCharacters = List.of(asterixCharacter1,asterixCharacter2);
        CharacterRepository mockRepository = mock(CharacterRepository.class);
        IdService mockIdService = mock(IdService.class);
        AsterixCharacterService asterixCharacterService = new AsterixCharacterService(mockRepository, mockIdService);
        when(mockRepository.findByProfession("Supplier")).thenReturn(asterixCharacters);

        // WHEN
        var actual = asterixCharacterService.getCharactersByProfession("Supplier");

        // THEN
        assertEquals(asterixCharacters.size(),actual.size());
        verify(mockRepository).findByProfession("Supplier");
    }


}