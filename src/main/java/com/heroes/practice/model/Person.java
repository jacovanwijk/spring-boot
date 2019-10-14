package com.heroes.practice.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Person {
    private UUID id;
    private String name;

    public Person(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
