package com.example.newyear.Service;

import com.example.newyear.Dto.Request.LoginRequestDto;
import com.example.newyear.Entity.Member;
import com.example.newyear.Repository.MemberRepository;
import com.example.newyear.Response.ResponseService;
import com.example.newyear.Response.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ResponseService responseService;


    /**
     * 로그인 인증
     */
    public SingleResponse getMemberByLoginId(LoginRequestDto loginRequestDto){
        Member member = memberRepository.findByLoginId(loginRequestDto.getLoginId());

        if (member == null){
            throw new RuntimeException("멤버가 없음");
        }

        if( !member.getPassword().equals(loginRequestDto.getPassword())){
            throw new RuntimeException("패스워드 에러");
        }


        return responseService.getSingleResponse(member);
    }



}
