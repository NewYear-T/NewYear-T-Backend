package com.example.newyear.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeRequestDto {
    private String title; // 챌린지 제목

    private String description; // 챌린지 설명

    private LocalDateTime startTime; // 챌린지 시작 시간

    private LocalDateTime endTime; // 챌린지 종료 시간

    private int max_people; // 정원

    private LocalDateTime endAt;
}
