package com.example.newyear.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class CommentRequestDto {
    private Long id; // 댓글 id

    private String content; // 댓글 내용

    private Long memberId; // 멤버 정보

    private Long challengeId; // 챌린지 정보
}
