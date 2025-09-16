package org.example.bootcampspringasterixapi.model;

import lombok.With;

@With
public record Village(String id, String name, String country, String location) {
}
