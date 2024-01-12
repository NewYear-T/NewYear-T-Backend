package com.example.newyear.Service;

import com.example.newyear.Dto.Request.LoginRequestDto;
import com.example.newyear.Dto.Request.SignUpRequestDto;
import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.Member;
import com.example.newyear.Repository.ChallengeRepository;
import com.example.newyear.Repository.MemberRepository;
import com.example.newyear.Response.CommonResponse;
import com.example.newyear.Response.ResponseService;
import com.example.newyear.Response.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ResponseService responseService;

    @Autowired
    ChallengeRepository challengeRepository;


    /**
     * 로그인 인증
     */
    public Member getMemberByLoginId(LoginRequestDto loginRequestDto){
        Member member = memberRepository.findByLoginId(loginRequestDto.getLoginId());

        if (member == null){
            throw new RuntimeException("멤버가 없음");
        }

        if( !member.getPassword().equals(loginRequestDto.getPassword())){
            throw new RuntimeException("패스워드 에러");
        }


        return member;
    }

    /**
     * 챌린지 신청하기 (일반 유저가 맘에 드는 모임 신청하기)
     */

    @Transactional
    public SingleResponse joinChallenge(Member member, Long challengeId){
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 챌린지 "));

        challenge.getMembers().add(member);
        challengeRepository.save(challenge);
        return responseService.getSingleResponse(challenge);
    }


    public CommonResponse SignUp(SignUpRequestDto signUpRequestDto) {

        Member member = Member.builder()
                .userName(signUpRequestDto.getUserName())
                .loginId(signUpRequestDto.getLoginId())
                .password(signUpRequestDto.getPassword())
                .gender()

    }
}
