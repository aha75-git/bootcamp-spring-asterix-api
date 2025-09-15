package org.example.bootcampspringasterixapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.example.bootcampspringasterixapi.model.AsterixCharacter;

import java.util.List;

@Repository
public interface CharacterRepository extends MongoRepository<AsterixCharacter,String> {
    List<AsterixCharacter> findByProfession(String profession);
}
