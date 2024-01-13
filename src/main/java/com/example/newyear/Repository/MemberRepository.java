package com.example.newyear.Repository;

import com.example.newyear.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m from Member m where m.loginId = ?1")
    Member findByLoginId(String loginId);

    Optional<Member> findByUserName(String username);
}
