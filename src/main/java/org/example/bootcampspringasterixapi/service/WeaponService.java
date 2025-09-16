package org.example.bootcampspringasterixapi.service;

import org.example.bootcampspringasterixapi.model.Weapon;
import org.example.bootcampspringasterixapi.repository.WeaponRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeaponService {
    private final WeaponRepository weaponRepository;

    public WeaponService(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    public List<Weapon> getAllWeapons() {
        return  weaponRepository.findAll();
    }

    public Weapon createWeapon(Weapon weapon) {
        return weaponRepository.save(weapon);
    }

    public Weapon getWeaponById(String id) {
        return weaponRepository.findById(id).orElse(null);
    }

    public void deleteWeapon(String id) {
        weaponRepository.deleteById(id);
    }

    public Weapon updateWeapon(String id, Weapon weapon) {
        Weapon weaponOld = this.weaponRepository.findById(id).orElse(null);
        if  (weaponOld != null) {
            return this.weaponRepository.save(weaponOld
                    .withName(weapon.name())
                    .withType(weapon.type()));
        }
        return null;
    }
}
