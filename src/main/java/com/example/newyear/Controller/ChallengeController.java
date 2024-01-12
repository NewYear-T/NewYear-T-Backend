package com.example.newyear.Controller;

import com.example.newyear.Dto.Request.ChallengeRequestDto;
import com.example.newyear.Entity.Member;
import com.example.newyear.Response.CommonResponse;
import com.example.newyear.Response.ResponseService;
import com.example.newyear.Service.ChallengeService;
import com.example.newyear.Service.CompletedService;
import com.example.newyear.Service.RankingService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChallengeController {
    @Autowired
    CompletedService completedService;

    @Autowired
    ResponseService responseService;

    @Autowired
    ChallengeService challengeService;

    @Autowired
    RankingService rankingService;



    @PostMapping("/api/challenge")
    public CommonResponse addChallenge(@RequestBody ChallengeRequestDto challengeRequestDto, HttpSession session){

        Member member = (Member) session.getAttribute("member");

        challengeService.makeChallenge(challengeRequestDto);

        return new CommonResponse("챌린지 생성 성공");
    }

    /**
     * 챌린지 인증 성공 > 점수 얻음
     */
    @GetMapping("/api/challenge/complete/{challengeId}")
    public CommonResponse completeChallenge(@PathVariable("challengeId") Long challengeId, HttpSession session){
        Member member = (Member) session.getAttribute("member");

        CommonResponse response = completedService.completeChallenge(member,challengeId);

        return response;
    }

    @GetMapping("/{challengeId}/ranking")
    public ResponseEntity<List<String>> sortMember(@PathVariable Long challengeId) {
        List<String> members = rankingService.descMember(challengeId);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }
}
