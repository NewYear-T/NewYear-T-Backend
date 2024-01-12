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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Tag(name = "로그인 컨트롤러",description = "로그인, 회원가입")
public class MemberController {

    @Autowired
    MemberService memberService;

    @Operation(summary = "로그인", description = "로그인 기능입니다.")
    @PostMapping("/api/sign-in")
    public SingleResponse login(@RequestBody LoginRequestDto loginRequestDto, HttpSession session){
        SingleResponse response = memberService.getMemberByLoginId(loginRequestDto);


        return response;
    }

    /**
     * 세션에 유저가 유효한 지 아닌지
     */

}
