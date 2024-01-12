package com.example.newyear.Repository;

import com.example.newyear.Entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank,Long> {

    // 점수 증가 + 1점
    @Modifying
    @Transactional
    @Query("update Rank r set r.score = r.score + 1 where r.member.id = :memberId")
    void increaseScoreForMember(@Param("memberId") Long memberId);


    // 멤버 전체 1점 증가
    @Modifying
    @Query("update Rank r set r.score = r.score + 1 where r.challenge.id = ?1")
    void increaseScoreForMembers(Long challengeId);

    // 단체 점수 증가 +2점

    // 순위 리스트
}