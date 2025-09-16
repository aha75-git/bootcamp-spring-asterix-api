package org.example.bootcampspringasterixapi.controller;

import org.example.bootcampspringasterixapi.model.Pet;
import org.example.bootcampspringasterixapi.model.Village;
import org.example.bootcampspringasterixapi.service.VillageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/village")
public class VillageController {
    private final VillageService villageService;

    public VillageController(VillageService villageService) {
        this.villageService = villageService;
    }

    @GetMapping
    public List<Village> getAllVillages() {
        return villageService.getAllVillages();
    }

    @PostMapping
    public ResponseEntity<Village> createVillage(@RequestBody Village village) {
        Village createdVillage = villageService.createVillage(village);
        return new ResponseEntity<>(createdVillage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Village> getVillageById(@PathVariable String id) {
        Village village = villageService.getVillageById(id);
        if (village != null) {
            return new ResponseEntity<>(village, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVillage(@PathVariable String id) {
        villageService.deleteVillage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Village> updateVillage(@PathVariable String id, @RequestBody Village village) {
        Village villageSaved = this.villageService.updateVillage(id, village);
        if (villageSaved != null) {
            return new ResponseEntity<>(villageSaved, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
