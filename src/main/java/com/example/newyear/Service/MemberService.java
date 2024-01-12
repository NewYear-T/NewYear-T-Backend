package com.example.newyear.Service;

import com.example.newyear.Dto.ChallengeDto;
import com.example.newyear.Dto.Request.LoginRequestDto;
import com.example.newyear.Dto.Request.SignUpRequestDto;
import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.ChallengeMembers;
import com.example.newyear.Entity.Member;
import com.example.newyear.Entity.Ranking;
import com.example.newyear.Entity.enums.Gender;
import com.example.newyear.Entity.enums.Local;
import com.example.newyear.Entity.enums.Role;
import com.example.newyear.Repository.ChallengeMemberRepository;
import com.example.newyear.Repository.ChallengeRepository;
import com.example.newyear.Repository.MemberRepository;
import com.example.newyear.Repository.RankRepository;
import com.example.newyear.Response.CommonResponse;
import com.example.newyear.Response.ResponseService;
import com.example.newyear.Response.SingleResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    RankRepository rankRepository;


    /**
     * 로그인 인증
     */
    public Member getMemberByLoginId(LoginRequestDto loginRequestDto) {
            Member member = memberRepository.findByLoginId(loginRequestDto.getLoginId());

        if (member == null) {
            throw new RuntimeException("멤버가 없음");
        }

        if (!member.getPassword().equals(loginRequestDto.getPassword())) {
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
    public ChallengeDto joinChallenge(Member member, Long challengeId) {
        // 멤버와 챌린지 찾기
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found"));

        ChallengeMembers challengeMembers = ChallengeMembers.builder()
                .challenge(challenge)
                .member(member).build();

        if (challenge.getCurrent_people() < challenge.getMax_people()) {
            challenge.setCurrent_people(challenge.getCurrent_people() + 1);
        } else {
            throw new IllegalStateException("Challenge is full. Cannot join.");
        }

        // 챌린지에 멤버 추가
        challengeMemberRepository.save(challengeMembers);

        Ranking ranking = Ranking.builder().challenge(challenge)
                .member(member)
                .score(0)
                .build();

        rankRepository.save(ranking);



        return ChallengeDto.from(challenge);
    }


    public CommonResponse SignUp(SignUpRequestDto signUpRequestDto) {

        Member existed = memberRepository.findByUserName(signUpRequestDto.getUserName());
        if (existed == null) {
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
        } else {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }

    }

    public List<ChallengeDto> myChallenge(Member member){
        List<ChallengeDto> challenges = challengeMemberRepository.findMyChallenge(member.getId()).stream().map(ChallengeDto::from)
                .collect(Collectors.toList());

        return challenges;
    }
}
