package com.example.newyear.Entity;


import com.example.newyear.Entity.enums.Local;
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
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @OneToMany(mappedBy = "challenge")
    private List<Completed> completedList;

    @OneToMany(mappedBy = "challenge")
    private List<UserChallenge> userChallenges;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
