package com.example.newyear.Repository;

import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.Completed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedRepository extends JpaRepository<Completed,Long> {



    @Query("select c from Completed c where c.challenge=?1")
    List<Completed> findAllByChallengeId(Challenge challenge);
}
