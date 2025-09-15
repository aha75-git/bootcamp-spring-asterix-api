package org.example.bootcampspringasterixapi.controller;

import org.example.bootcampspringasterixapi.dto.AsterixCharacterDto;
import org.example.bootcampspringasterixapi.service.AsterixCharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.example.bootcampspringasterixapi.model.AsterixCharacter;

@RestController
@RequestMapping("/asterix/characters")
public class AsterixController {

    private final AsterixCharacterService asterixCharacterService;

    public AsterixController(AsterixCharacterService asterixCharacterService) {
        this.asterixCharacterService = asterixCharacterService;
    }

    @GetMapping
    public List<AsterixCharacter> getCharacters() {
        return this.asterixCharacterService.getCharacters();
    }

    @GetMapping("/{id}")
    public AsterixCharacter getCharactersById(@PathVariable String id) {
        return this.asterixCharacterService.getCharactersById(id);
    }

    @PostMapping
    public AsterixCharacter addCharacter(@RequestBody AsterixCharacterDto asterixCharacterDto) {
        return this.asterixCharacterService.addCharacter(asterixCharacterDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        this.asterixCharacterService.deleteCharacter(id);
    }

    @PutMapping("/{id}")
    public AsterixCharacter updateCharacter(@PathVariable String id,  @RequestBody AsterixCharacterDto asterixCharacterDto) {
        return this.asterixCharacterService.updateCharacter(id, asterixCharacterDto);
    }

    @GetMapping("/search")
    public List<AsterixCharacter> getCharactersByProfession(@RequestParam String profession) {
        return this.asterixCharacterService.getCharactersByProfession(profession);
    }

    @GetMapping("/averageage/{profession}")
    public double getAverageAgeByProfession (@PathVariable String profession) {
        return this.asterixCharacterService.getAverageAgeByProfession(profession);
    }
}
