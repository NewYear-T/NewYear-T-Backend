package com.example.newyear.Repository;

import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.Completed;
import com.example.newyear.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedRepositoey extends JpaRepository<Completed,Long> {


    boolean existsByMemberAndChallengeAndCompleted(Member member, Challenge challenge, boolean b);
}
