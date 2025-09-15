package org.example.bootcampspringasterixapi.model;

import lombok.With;

@With
public record AsterixCharacter(String id, String name, Integer age, String profession) {

}
