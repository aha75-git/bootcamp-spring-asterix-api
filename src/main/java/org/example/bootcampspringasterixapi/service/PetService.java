package org.example.bootcampspringasterixapi.service;

import org.example.bootcampspringasterixapi.model.Pet;
import org.example.bootcampspringasterixapi.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPetById(String id) {
        return petRepository.findById(id).orElse(null);
    }

    public void deletePet(String id) {
        petRepository.deleteById(id);
    }
}
