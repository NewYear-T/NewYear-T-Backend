package com.example.newyear.Service;

import com.example.newyear.Dto.Request.CommentRequestDto;
import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.Comment;
import com.example.newyear.Entity.Member;
import com.example.newyear.Repository.ChallengeRepository;
import com.example.newyear.Repository.CommentRepository;
import com.example.newyear.Repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
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
    public CommentRequestDto addComment(Long memberId, Long challengeId, CommentRequestDto commentRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다.."));
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() ->
                new IllegalArgumentException("해당 챌린지가 존재하지 않습니다.."));

        Comment comment = Comment.builder()
                .id(commentRequestDto.getId())
                .content(commentRequestDto.getContent())
                .member(member)
                .challenge(challenge)
                .build();

        commentRepository.save(comment);

        return reqToDto(comment);
    }

    /**
     * 댓글 전체 조회
     */
    public List<CommentRequestDto> findAll(Long challengeId) {
        List<Comment> commentList = commentRepository.findByChallengeId(challengeId);
        List<CommentRequestDto> commentRequestDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentRequestDtoList.add(reqToDto(comment));
        }
        return commentRequestDtoList;
    }

    /**
     * 댓글 수정
     */
    public CommentRequestDto updateComment(Long commentId, CommentRequestDto updateParam) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다."));
        comment.setContent(updateParam.getContent());
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
     * Entity -> Dto
     */
    private CommentRequestDto reqToDto(Comment comment) {
        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .memberId(comment.getMember().getId())
                .challengeId(comment.getChallenge().getId())
                .build();
        return commentRequestDto;
    }

}
