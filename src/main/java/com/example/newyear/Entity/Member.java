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
/**
 * 사용자 정보
 */
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName; // 이름

    private String loginId; // 아이디

    private String password; // 패스워드

    @Enumerated(value = EnumType.STRING)
    private Role role; // 권한 [ROLE_MEMBER, ROLE_ADMIN]

    private Gender gender; // 성별

    private Local local; // 행정구역 ( 지역 )

    private int age; // 나이

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Category> categoryList; // 카테고리 리스트

    @ManyToMany
    @JoinTable(name = "user_challenge",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "challenge_id"))
    private List<Challenge> challenges; // 챌린지 리스트

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Comment> comments; // 댓글 리스트

//    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
//    private List<ChallengeMembers> challengeMembers;

}
