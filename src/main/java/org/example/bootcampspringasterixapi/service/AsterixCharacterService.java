package org.example.bootcampspringasterixapi.service;

import org.example.bootcampspringasterixapi.dto.AsterixCharacterDto;
import org.example.bootcampspringasterixapi.model.AsterixCharacter;
import org.example.bootcampspringasterixapi.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class AsterixCharacterService {
    private final CharacterRepository characterRepository;
    private final IdService idService;

    public AsterixCharacterService(CharacterRepository characterRepository,  IdService idService) {
        this.characterRepository = characterRepository;
        this.idService = idService;
    }

    public List<AsterixCharacter> getCharacters() {
        return this.characterRepository.findAll();
    }

    public AsterixCharacter getCharactersById(String id) {
        return this.characterRepository.findById(id).orElse(null);
    }

    public AsterixCharacter addCharacter(AsterixCharacterDto asterixCharacterDto) {
        return this.characterRepository.save(
                new AsterixCharacter(idService.randomId(),
                asterixCharacterDto.name(),
                asterixCharacterDto.age(),
                asterixCharacterDto.profession()));
    }

    public void deleteCharacter(String id) {
        this.characterRepository.deleteById(id);
    }

    public AsterixCharacter updateCharacter( String id, AsterixCharacterDto asterixCharacterDto) {
        AsterixCharacter asterixCharacter = null;
        AsterixCharacter asterixCharacterOld = this.characterRepository.findById(id).orElse(null);
        if (asterixCharacterOld != null) {
            asterixCharacter = this.characterRepository.save(asterixCharacterOld
                    .withName(asterixCharacterDto.name())
                    .withAge(asterixCharacterDto.age())
                    .withProfession(asterixCharacterDto.profession())
            );
        }
        return asterixCharacter;
    }

    public List<AsterixCharacter> getCharactersByProfession(String profession) {
        return this.characterRepository.findByProfession(profession);
    }

    public double getAverageAgeByProfession (String profession) {
        return this.characterRepository.findByProfession(profession)
                .stream()
                .mapToDouble(AsterixCharacter::age)
                .average()
                .orElse(0);
    }
}
