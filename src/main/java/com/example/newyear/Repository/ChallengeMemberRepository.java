package com.example.newyear.Repository;

import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.ChallengeMembers;
import com.example.newyear.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeMemberRepository  extends JpaRepository<ChallengeMembers,Long> {
    @Query("select m.member from ChallengeMembers m where m.challenge.id = ?1")
    List<Member> findAllByChallengeId(Long challengeId);


    @Query("select cm.challenge from ChallengeMembers  cm where cm.member.id = ?1")
    List<Challenge> findMyChallenge(Long memberId);
}
