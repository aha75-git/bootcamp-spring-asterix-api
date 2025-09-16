package org.example.bootcampspringasterixapi.model;

import lombok.With;

@With
public record Pet(String id, String name, String type) {
}
