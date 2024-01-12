package com.example.newyear.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * 챌린지 완료 여부 체크
 */
public class Completed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean completed; // 완료 여부

    @CreationTimestamp
    private LocalDateTime completedAt; // 완료 시간

    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge; // 챌린지 정보
}
