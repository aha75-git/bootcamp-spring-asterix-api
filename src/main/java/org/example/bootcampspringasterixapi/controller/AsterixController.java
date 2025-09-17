package org.example.bootcampspringasterixapi.controller;

import org.example.bootcampspringasterixapi.dto.AsterixCharacterDto;
import org.example.bootcampspringasterixapi.model.Pet;
import org.example.bootcampspringasterixapi.service.AsterixCharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AsterixCharacter> getCharactersById(@PathVariable String id) {
        AsterixCharacter asterixCharacter = this.asterixCharacterService.getCharactersById(id);
        if (asterixCharacter != null) {
            return new ResponseEntity<>(asterixCharacter, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public AsterixCharacter addCharacter(@RequestBody AsterixCharacterDto asterixCharacterDto) {
        return this.asterixCharacterService.addCharacter(asterixCharacterDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable String id) {
        this.asterixCharacterService.deleteCharacter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
