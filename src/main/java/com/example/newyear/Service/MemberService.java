package com.example.newyear.Service;

import com.example.newyear.Dto.ChallengeDto;
import com.example.newyear.Dto.Request.LoginRequestDto;
import com.example.newyear.Dto.Request.SignUpRequestDto;
import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.ChallengeMembers;
import com.example.newyear.Entity.Member;
import com.example.newyear.Entity.enums.Gender;
import com.example.newyear.Entity.enums.Local;
import com.example.newyear.Entity.enums.Role;
import com.example.newyear.Repository.ChallengeMemberRepository;
import com.example.newyear.Repository.ChallengeRepository;
import com.example.newyear.Repository.MemberRepository;
import com.example.newyear.Response.CommonResponse;
import com.example.newyear.Response.ResponseService;
import com.example.newyear.Response.SingleResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ResponseService responseService;

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ChallengeMemberRepository challengeMemberRepository;


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
     *
     * @return
     */

    @Transactional
    public SingleResponse joinChallenge(Member member, Long challengeId) {
        // 멤버와 챌린지 찾기
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));

        ChallengeMembers challengeMembers = ChallengeMembers.builder()
                .challenge(challenge)
                        .member(member).build();

        // 챌린지에 멤버 추가
        challengeMemberRepository.save(challengeMembers);

        // 변경 사항 저장
        challengeRepository.save(challenge);
        memberRepository.save(member);
        return responseService.getSingleResponse(ChallengeDto.from(challenge));
    }


    public CommonResponse SignUp(SignUpRequestDto signUpRequestDto) {

        Member member = Member.builder()
                .userName(signUpRequestDto.getUserName())
                .loginId(signUpRequestDto.getLoginId())
                .password(signUpRequestDto.getPassword())
                .gender(Gender.findByDescription(signUpRequestDto.getGender()))
                .local(Local.findByDescription(signUpRequestDto.getLocal()))
                .age(signUpRequestDto.getAge())
                .role(Role.ROLE_MEMBER)
                .build();

        memberRepository.save(member);

        return new CommonResponse("성공적으로 회원가입 했습니다.");

    }
}
