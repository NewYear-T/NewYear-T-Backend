package com.example.newyear.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
public class CommentRequestDto {
    private String content; // 댓글 내용

    @CreationTimestamp
    private LocalDateTime createdAt; // 등록 시간

    private Long memberId; // 멤버 정보

    private Long challengeId; // 챌린지 정보
}
