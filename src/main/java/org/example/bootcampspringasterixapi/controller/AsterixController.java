package org.example.bootcampspringasterixapi.controller;

import org.example.bootcampspringasterixapi.repository.CharacterRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.example.bootcampspringasterixapi.model.AsterixCharacter;

@RestController
@RequestMapping("/asterix/characters")
public class AsterixController {

    private final CharacterRepository characterRepository;

    public AsterixController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping
    public List<AsterixCharacter> getCharacters() {
        return this.characterRepository.findAll();
    }

    @GetMapping("/{id}")
    public AsterixCharacter getCharactersById(@PathVariable String id) {
        return this.characterRepository.findById(id).orElse(null);
    }

    @PostMapping
    public AsterixCharacter addCharacter(@RequestBody AsterixCharacter asterixCharacter) {
        return this.characterRepository.save(asterixCharacter);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        this.characterRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public AsterixCharacter updateCharacter(@PathVariable String id,  @RequestBody AsterixCharacter asterixCharacter) {
        AsterixCharacter asterixCharacterOld = this.characterRepository.findById(id).orElse(null);
        if (asterixCharacterOld != null) {
            this.characterRepository.save(asterixCharacterOld
                    .withName(asterixCharacter.name())
                    .withAge(asterixCharacter.age())
                    .withProfession(asterixCharacter.profession())
            );
        }
        return asterixCharacter;
    }

    @GetMapping("/search")
    public List<AsterixCharacter> getCharactersByProfession(@RequestParam String profession) {
        return this.characterRepository.findByProfession(profession);
    }

    @GetMapping("/averageage/{profession}")
    public double getAverageAgeByProfession (@PathVariable String profession) {
        return this.characterRepository.findByProfession(profession)
                .stream()
                .mapToDouble(AsterixCharacter::age)
                .average()
                .orElse(0);
    }
}
