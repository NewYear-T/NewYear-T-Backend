package com.example.newyear.Service;

import com.example.newyear.Dto.Request.CommentRequestDto;
import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.Comment;
import com.example.newyear.Entity.Member;
import com.example.newyear.Repository.ChallengeRepository;
import com.example.newyear.Repository.CommentRepository;
import com.example.newyear.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ChallengeRepository challengeRepository;

    /**
     * 댓글 추가
     */
    public CommentRequestDto addComment(Long challengeId, Long memberId, CommentRequestDto commentRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다.."));
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() ->
                new IllegalArgumentException("해당 챌린지가 존재하지 않습니다.."));

        Comment comment = reqToEntity(commentRequestDto);
        comment.setMember(member);
        comment.setChallenge(challenge);
        commentRepository.save(comment);

        return reqToDto(comment);
    }


    /**
     * 댓글 수정
     */
    public CommentRequestDto updateComment(Long commentId, CommentRequestDto updateParam) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다."));
        comment.setContent(updateParam.getContent());
        comment.setCreatedAt(updateParam.getCreatedAt());
        commentRepository.save(comment);
        return reqToDto(comment);
    }

    /**
     * 댓글 삭제
     */
    public CommentRequestDto deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다."));
        commentRepository.deleteById(commentId);
        return reqToDto(comment);
    }


    /**
     * Dto -> Entity
     */
    private Comment reqToEntity(CommentRequestDto commentRequestDto) {
        Optional<Member> byId = memberRepository.findById(commentRequestDto.getMemberId());
        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .createdAt(commentRequestDto.getCreatedAt())
                .member(byId.get())
                .build();
        return comment;
    }

    /**
     * Entity -> Dto
     */
    private CommentRequestDto reqToDto(Comment comment) {
        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .memberId(comment.getMember().getId())
                .build();
        return commentRequestDto;
    }

}
