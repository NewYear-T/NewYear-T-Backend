package com.example.newyear.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**

 챌린지 정보*/
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // 챌린지 제목

    private String description; // 챌린지 설명

    private LocalDateTime startTime; // 챌린지 시작 시간

    private LocalDateTime endTime; // 챌린지 종료 시간

    private int max_people; // 정원

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 시간

    private LocalDateTime endAt; // 모집 마감 시간

    @OneToMany(mappedBy = "challenge")
    private List<Completed> completedList; // 완료 여부

    @ManyToMany(mappedBy = "challenges")
    private List<Member> members = new ArrayList<>(); // 멤버 정보

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}