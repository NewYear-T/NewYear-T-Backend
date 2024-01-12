package com.example.newyear.Service;

import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.Completed;
import com.example.newyear.Entity.Member;
import com.example.newyear.Repository.ChallengeRepository;
import com.example.newyear.Repository.CompletedRepositoey;
import com.example.newyear.Repository.MemberRepository;
import com.example.newyear.Repository.RankRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompletedService {

    @Autowired
    private CompletedRepositoey completedRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Transactional
    public void completeChallenge(Long memberId, Long challengeId) {
        // 사용자의 인증 처리
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new EntityNotFoundException("Challenge not found"));

        Completed completed = new Completed();
        completed.setMember(member);
        completed.setChallenge(challenge);
        completed.setCompleted(true);

        completedRepository.save(completed);

        // 사용자 Rank 점수 증가
        rankRepository.increaseScoreForMember(memberId);

        // 모든 인원이 인증 완료했는지 확인
        if (isChallengeCompletedByAllMembers(challenge)) {
            // 모든 사용자의 Rank 점수 추가 증가
            rankRepository.increaseScoreForMembers(challengeId);
        }
    }

    private boolean isChallengeCompletedByAllMembers(Challenge challenge) {
        List<Member> members = challenge.getMembers();
        for (Member member : members) {
            if (!completedRepository.existsByMemberAndChallengeAndCompleted(member, challenge, true)) {
                return false;
            }
        }
        return true;
    }
}
