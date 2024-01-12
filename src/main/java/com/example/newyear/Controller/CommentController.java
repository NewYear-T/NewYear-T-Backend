package com.example.newyear.Controller;

import com.example.newyear.Dto.Request.CommentRequestDto;
import com.example.newyear.Entity.Member;
import com.example.newyear.Service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Comment Controller")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Operation(summary = "댓글 추가 API", description = "댓글을 추가하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 등록되었습니다.."),
            @ApiResponse(responseCode = "404", description = "댓글 등록에 실패하였습니다..")
    })
    @GetMapping("/{challengeId}/comment/add")
    public ResponseEntity<CommentRequestDto> addComment(@PathVariable Long challengeId, @RequestBody CommentRequestDto commentRequestDto, HttpSession httpSession) {
        Member member = (Member) httpSession.getAttribute("member");
        CommentRequestDto responseDto = commentService.addComment(challengeId, member.getId(), commentRequestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "댓글 수정 API", description = "댓글을 수정하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 수정되었습니다..."),
            @ApiResponse(responseCode = "404", description = "댓글 수정에 실패하였습니다..")
    })
    @PatchMapping("/{commentId}/update")
    public ResponseEntity<CommentRequestDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        CommentRequestDto responseDto = commentService.updateComment(commentId, commentRequestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "댓글 삭제 API", description = "댓글을 삭제하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 삭제되었습니다..."),
            @ApiResponse(responseCode = "404", description = "댓글 삭제에 실패하였습니다..")
    })
    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<CommentRequestDto> deleteComment(@PathVariable Long commentId) {
        CommentRequestDto responseDto = commentService.deleteComment(commentId);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
