package com.example.newyear.Repository;

import com.example.newyear.Dto.ChallengeDto;
import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.enums.Gender;
import com.example.newyear.Entity.enums.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    @Query("select c from Challenge c where c.category.id = ?1")
    List<Challenge> findAllByCategoryName(Long categoryId);

    //    @Query("select c from Challenge c where c.category.id= :categoryId " +
//            " (:gender is null or c.createdBy.gender = :gender) and (:local is null or c.createdBy.local = :local) ")
//    List<Challenge> findChallengeList(@Param("categoryId") Long categoryId,@Param("gender") Gender gender,@Param("local") Local local);
    @Query("select c from Challenge c where c.category.id = ?1")
    List<Challenge> findChallengesByCategory(Long categoryId);

    @Query("select c from Challenge c where c.category.id = ?1 and c.createdBy.local = ?2")
    List<Challenge> findChallengesByCategoryAndLocal(Long categoryId, Local local);

    @Query("select c from Challenge c where c.category.id = ?1 and c.createdBy.gender = ?2")
    List<Challenge> findChallengesByCategoryAndGender(Long categoryId, Gender gender);

    @Query("select c from Challenge c where c.category.id = ?1 and c.createdBy.gender = ?2 and c.createdBy.local = ?3")
    List<Challenge> findChallengesByCategoryAndGenderAndLocal(Long categoryId, Gender gender, Local local);
}
