package com.example.newyear.Entity.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남자"),FEMALE("여자");

    private String description;

    Gender(String description){ this.description = description;}

    public static Gender findByDescription(String description) {
        for (Gender gender : values()) {
            if (gender.getDescription().equals(description)) {
                return gender;
            }
        }
        return null; // 혹은 적절한 예외 처리
    }
}
