package com.example.newyear.Controller;

import com.example.newyear.Dto.Request.LoginRequestDto;
import com.example.newyear.Entity.Member;
import com.example.newyear.Response.SingleResponse;
import com.example.newyear.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/api/sign-in")
    public SingleResponse login(@RequestBody LoginRequestDto loginRequestDto, HttpSession session){
        SingleResponse response = memberService.getMemberByLoginId(loginRequestDto);


        return response;
    }
}
