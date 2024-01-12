package com.example.newyear.Service;

import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.Completed;
import com.example.newyear.Entity.Member;
import com.example.newyear.Repository.*;
import com.example.newyear.Response.CommonResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompletedService {

    @Autowired
    private CompletedRepository completedRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeMemberRepository challengeMemberRepository;

    @Transactional
    public CommonResponse completeChallenge(Member member, Long challengeId) {
        // 사용자의 인증 처리
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new EntityNotFoundException("Challenge not found"));

        Completed completed = new Completed();
        completed.setMember(member);
        completed.setChallenge(challenge);
        completed.setCompleted(true);

        completedRepository.save(completed);

        // 사용자 Rank 점수 증가
        rankRepository.increaseScoreForMember(member.getId());

        // 모든 인원이 인증 완료했는지 확인
        if (isChallengeCompletedByAllMembers(challenge)) {
            // 모든 사용자의 Rank 점수 추가 증가
            rankRepository.increaseScoreForMembers(challengeId);
            return new CommonResponse("전체 사용자 1점 추가");

        }

        return new CommonResponse(member.getUserName() + "1점 추가");
    }



    private boolean isChallengeCompletedByAllMembers(Challenge challenge){
        List<Completed> completedList = completedRepository.findAllByChallengeId(challenge);
        for(Completed  completed : completedList){
            if(!completed.getCompleted()){
                return false;
            }
        }
        return true;
    }
}
