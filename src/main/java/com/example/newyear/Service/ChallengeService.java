package com.example.newyear.Service;

import com.example.newyear.Dto.Request.ChallengeRequestDto;
import com.example.newyear.Entity.Challenge;
import com.example.newyear.Repository.ChallengeRepository;
import com.example.newyear.Response.CommonResponse;
import com.example.newyear.Response.ResponseService;
import com.example.newyear.Response.SingleResponse;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ResponseService responseService;

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
}
