package com.example.newyear.Repository;

import com.example.newyear.Entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge,Long> {
    @Query("select c from Challenge c where c.category.id = ?1")
    List<Challenge> findAllByCategoryName(Long categoryId);
}
