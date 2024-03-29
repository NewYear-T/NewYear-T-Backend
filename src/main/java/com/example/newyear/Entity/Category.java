package com.example.newyear.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * 카테고리
 */
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member; // 유저 정보

    private String categoryName; // 카테고리 이름

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Challenge> challengeList;

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 시간
}
