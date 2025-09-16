package org.example.bootcampspringasterixapi.repository;

import org.example.bootcampspringasterixapi.model.Weapon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends MongoRepository<Weapon,String> {
}
