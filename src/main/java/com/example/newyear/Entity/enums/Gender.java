package com.example.newyear.Entity.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남자"),FEMALE("여자");

    private String description;

    Gender(String description){ this.description = description;}
}
