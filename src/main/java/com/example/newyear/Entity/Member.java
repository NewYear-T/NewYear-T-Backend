package com.example.newyear.Entity;

import com.example.newyear.Entity.enums.Gender;
import com.example.newyear.Entity.enums.Local;
import com.example.newyear.Entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private Gender gender;

    private Local local;

    private int age;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Category> categoryList;

    @OneToMany(mappedBy = "member")
    private List<UserChallenge> userChallenges;



}
