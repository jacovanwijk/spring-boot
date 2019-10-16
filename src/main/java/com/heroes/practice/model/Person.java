package com.heroes.practice.model;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
public class Person {
    private UUID id;
    @NotBlank
    private String name;

    public Person(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
