package org.example.bootcampspringasterixapi.repository;

import org.example.bootcampspringasterixapi.model.Village;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageRepository extends MongoRepository<Village,String> {
}
