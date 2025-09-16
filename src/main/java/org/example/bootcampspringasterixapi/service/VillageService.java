package org.example.bootcampspringasterixapi.service;

import org.example.bootcampspringasterixapi.model.Village;
import org.example.bootcampspringasterixapi.repository.VillageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VillageService {
    private final VillageRepository  villageRepository;

    public VillageService(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }
    public List<Village> getAllVillages() {
        return villageRepository.findAll();
    }

    public Village createVillage(Village village) {
        return villageRepository.save(village);
    }

    public Village getVillageById(String id) {
        return villageRepository.findById(id).orElse(null);
    }

    public void deleteVillage(String id) {
        villageRepository.deleteById(id);
    }

    public Village updateVillage(String id, Village village) {
        Village villageOld = this.villageRepository.findById(id).orElse(null);
        if  (villageOld != null) {
            return this.villageRepository.save(villageOld
                    .withName(village.name())
                    .withCountry(village.country())
                    .withLocation(village.location()));
        }
        return null;
    }
}
