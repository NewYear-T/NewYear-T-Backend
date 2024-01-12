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
public class Completed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean completed;

    @CreationTimestamp
    private LocalDateTime completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;
}
