package com.example.newyear.Controller;

import com.example.newyear.Dto.Request.LoginRequestDto;
import com.example.newyear.Entity.Member;
import com.example.newyear.Response.SingleResponse;
import com.example.newyear.Service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Member Controller")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Operation(summary = "로그인 API", description = "로그인 하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 로그인되었습니다.."),
            @ApiResponse(responseCode = "404", description = "해당 유저가 존재하지 않습니다.")
    })
    @PostMapping("/sign-in")
    public SingleResponse login(@RequestBody LoginRequestDto loginRequestDto, HttpSession session){
        SingleResponse response = memberService.getMemberByLoginId(loginRequestDto);

        session.setAttribute("member", response.getData());

        session.getAttribute("member");

        return response;
    }







    @Operation(summary = "챌린지 참가 신청 API", description = "챌린지에 참가 신청하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 신청되었습니다."),
            @ApiResponse(responseCode = "404", description = "올바른 요청이 아닙니다.")
    })
    @GetMapping("/{challengeId}/apply")
    public SingleResponse challengeApply(@PathVariable Long challengeId, HttpSession httpSession) {
        Member member = (Member) httpSession.getAttribute("member");
        SingleResponse response = memberService.joinChallenge(member, challengeId);

;        return response;
    }
}
