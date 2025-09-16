package org.example.bootcampspringasterixapi.controller;

import org.example.bootcampspringasterixapi.model.Weapon;
import org.example.bootcampspringasterixapi.service.WeaponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weapons")
public class WeaponController {
    private final WeaponService weaponService;

    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @GetMapping
    public List<Weapon> getAllWeapons() {
        return weaponService.getAllWeapons();
    }

    @PostMapping
    public ResponseEntity<Weapon> createWeapon(@RequestBody Weapon weapon) {
        Weapon createdWeapon = weaponService.createWeapon(weapon);
        return new ResponseEntity<>(createdWeapon, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weapon> getWeaponById(@PathVariable String id) {
        Weapon weapon = weaponService.getWeaponById(id);
        if (weapon != null) {
            return new ResponseEntity<>(weapon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeapon(@PathVariable String id) {
        weaponService.deleteWeapon(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Weapon> updatePet(@PathVariable String id, @RequestBody Weapon weapon) {
        Weapon weaponSaved = this.weaponService.updateWeapon(id, weapon);
        if (weaponSaved != null) {
            return new ResponseEntity<>(weaponSaved, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}