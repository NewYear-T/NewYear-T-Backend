package com.example.newyear.Controller;

import com.example.newyear.Dto.Request.ChallengeRequestDto;
import com.example.newyear.Entity.Member;
import com.example.newyear.Response.CommonResponse;
import com.example.newyear.Response.ResponseService;
import com.example.newyear.Service.ChallengeService;
import com.example.newyear.Service.CompletedService;
import com.example.newyear.Service.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "챌린지 생성 API", description = "챌린지를 생성하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "챌린지 생성에 성공하였습니다."),
            @ApiResponse(responseCode = "404", description = "챌린지 생성에 실패하였습니다.")
    })
    @PostMapping("/api/challenge")
    public CommonResponse addChallenge(@RequestBody ChallengeRequestDto challengeRequestDto, HttpSession session){

        Member member = (Member) session.getAttribute("member");

        challengeService.makeChallenge(challengeRequestDto);

        return new CommonResponse("챌린지 생성 성공");
    }

    /**
     * 챌린지 인증 성공 > 점수 얻음
     */
    @Operation(summary = "챌린지 인증 후 점수 획득 API", description = "챌린지 인증 후 점수를 획득하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "점수 획득에 성공하였습니다."),
            @ApiResponse(responseCode = "404", description = "인증에 실패하였습니다.")
    })
    @GetMapping("/api/challenge/complete/{challengeId}")
    public CommonResponse completeChallenge(@PathVariable("challengeId") Long challengeId, HttpSession session){
        Member member = (Member) session.getAttribute("member");

        CommonResponse response = completedService.completeChallenge(member,challengeId);

        return response;
    }

    @Operation(summary = "랭킹 조회 API", description = "랭킹을 조회하는 API 입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "랭킹 조회에 성공하였습니다."),
            @ApiResponse(responseCode = "404", description = "랭킹이 존재하지 않습니다.")
    })
    @GetMapping("/{challengeId}/ranking")
    public ResponseEntity<List<String>> sortMember(@PathVariable Long challengeId) {
        List<String> members = rankingService.descMember(challengeId);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }
}
