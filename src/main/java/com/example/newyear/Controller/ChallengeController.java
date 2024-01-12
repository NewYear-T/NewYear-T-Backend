package com.example.newyear.Controller;

import com.example.newyear.Entity.Member;
import com.example.newyear.Response.ResponseService;
import com.example.newyear.Service.CompletedService;
import com.example.newyear.Service.RankingService;
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
    RankingService rankingService;

    @GetMapping("/{challengeId}/ranking")
    public ResponseEntity<List<String>> sortMember(@PathVariable Long challengeId) {
        List<String> members = rankingService.descMember(challengeId);

        return new ResponseEntity<>(members, HttpStatus.OK);
    }
}
