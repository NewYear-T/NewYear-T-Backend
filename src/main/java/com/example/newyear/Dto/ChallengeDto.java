package com.example.newyear.Dto;


import com.example.newyear.Dto.Request.ChallengeRequestDto;
import com.example.newyear.Entity.Challenge;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeDto {
    private Long id;

    private String title; // 챌린지 제목

    private String description; // 챌린지 설명

    private LocalDateTime startTime; // 챌린지 시작 시간

    private LocalDateTime endTime; // 챌린지 종료 시간

    private int max_people; // 정원

    private LocalDateTime endAt;

    public static ChallengeDto from(Challenge challenge){
        return new ChallengeDto(
                challenge.getId(),
                challenge.getTitle(),
                challenge.getDescription(),
                challenge.getStartTime(),
                challenge.getEndTime(),
                challenge.getMax_people(),
                challenge.getEndAt()
        );
    }

}
