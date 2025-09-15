package org.example.bootcampspringasterixapi.dto;

import lombok.With;

@With
public record AsterixCharacterDto(String name, Integer age, String profession) {
}
