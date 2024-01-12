package com.example.newyear.Service;

import com.example.newyear.Dto.ChallengeDto;
import com.example.newyear.Dto.Request.ChallengeRequestDto;
import com.example.newyear.Entity.Challenge;
import com.example.newyear.Repository.ChallengeRepository;
import com.example.newyear.Repository.MemberRepository;
import com.example.newyear.Repository.RankRepository;
import com.example.newyear.Response.CommonResponse;
import com.example.newyear.Response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ResponseService responseService;

    @Autowired
    RankRepository rankRepository;

    /**
     * 챌린지 만들기 (방만들기)
     */
    @Transactional
    public CommonResponse makeChallenge(ChallengeRequestDto challengeRequestDto){
        Challenge challenge = Challenge.builder()
                .title(challengeRequestDto.getTitle())
                .description(challengeRequestDto.getDescription())
                .startTime(challengeRequestDto.getStartTime())
                .endTime(challengeRequestDto.getEndTime())
                .endAt(challengeRequestDto.getEndAt())
                .max_people(challengeRequestDto.getMax_people())
                .build();

        challengeRepository.save(challenge);

        return new CommonResponse("성공적으로 업로드 되었습니다.");
    }


    public List<ChallengeDto> getChallengesByCategory(Long categoryId) {
        List<ChallengeDto> challengeDtoList = challengeRepository.findAllByCategoryName(categoryId)
                .stream().map(ChallengeDto::from).collect(Collectors.toList());

        return challengeDtoList;
    }
}
