package org.example.bootcampspringasterixapi.repository;

import org.example.bootcampspringasterixapi.model.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends MongoRepository<Pet,String> {
}
