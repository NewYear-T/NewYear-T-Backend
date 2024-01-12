package com.example.newyear.Controller;

import com.example.newyear.Dto.Request.LoginRequestDto;
import com.example.newyear.Entity.Member;
import com.example.newyear.Response.SingleResponse;
import com.example.newyear.Service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Api(tags = "Member Controller")
public class MemberController {

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "로그인 API", notes = "로그인 하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공적으로 로그인되었습니다.."),
            @ApiResponse(responseCode = "404", description = "해당 유저가 존재하지 않습니다.")
    })
    @PostMapping("/api/sign-in")
    public SingleResponse login(@RequestBody LoginRequestDto loginRequestDto, HttpSession session){
        SingleResponse response = memberService.getMemberByLoginId(loginRequestDto);


        return response;
    }

    /**
     * 세션에 유저가 유효한 지 아닌지
     */

}
