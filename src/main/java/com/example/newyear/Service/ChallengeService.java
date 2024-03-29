package com.example.newyear.Service;

import com.example.newyear.Dto.ChallengeDto;
import com.example.newyear.Dto.Request.ChallengeRequestDto;
import com.example.newyear.Entity.Category;
import com.example.newyear.Entity.Challenge;
import com.example.newyear.Entity.Member;
import com.example.newyear.Entity.enums.Gender;
import com.example.newyear.Entity.enums.Local;
import com.example.newyear.Repository.CategoryRepository;
import com.example.newyear.Repository.ChallengeRepository;
import com.example.newyear.Repository.MemberRepository;
import com.example.newyear.Repository.RankRepository;
import com.example.newyear.Response.CommonResponse;
import com.example.newyear.Response.ResponseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ResponseService responseService;

    @Autowired
    RankRepository rankRepository;

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * 챌린지 만들기 (방만들기)
     */
    @Transactional
    public CommonResponse makeChallenge(ChallengeRequestDto challengeRequestDto, Member member){
        Optional<Category> category = categoryRepository.findById(challengeRequestDto.getCategoryId());
        Challenge challenge = Challenge.builder()
                .title(challengeRequestDto.getTitle())
                .description(challengeRequestDto.getDescription())
                .startTime(challengeRequestDto.getStartTime())
                .endTime(challengeRequestDto.getEndTime())
                .endAt(challengeRequestDto.getEndAt())
                .current_people(challengeRequestDto.getCurrent_people() + 1)
                .max_people(challengeRequestDto.getMax_people())
                .category(category.get())
                .createdBy(member)
                .build();

        challengeRepository.save(challenge);

        return new CommonResponse("성공적으로 업로드 되었습니다.");
    }


    public List<ChallengeDto> getChallengesByCategory(Long categoryId) {
        List<ChallengeDto> challengeDtoList = challengeRepository.findAllByCategoryName(categoryId)
                .stream().map(ChallengeDto::from).collect(Collectors.toList());

        return challengeDtoList;
    }

    public List<ChallengeDto> getChallengesBy(Long categoryId, String gender1, String local1) {
        Gender gender = Gender.findByDescription(gender1);
        Local local = Local.findByDescription(local1);

        List<Challenge> challenges;

        if (gender == null && local == null) {
            challenges = challengeRepository.findChallengesByCategory(categoryId);
        } else if (gender == null) {
            challenges = challengeRepository.findChallengesByCategoryAndLocal(categoryId, local);
        } else if (local == null) {
            challenges = challengeRepository.findChallengesByCategoryAndGender(categoryId, gender);
        } else {
            challenges = challengeRepository.findChallengesByCategoryAndGenderAndLocal(categoryId, gender, local);
        }


        List<ChallengeDto> challengeDtoList = challenges.stream().map(ChallengeDto::from).collect(Collectors.toList());

        return challengeDtoList;
    }

    public ChallengeDto findById(Long challengeId) {
        ChallengeDto challengeDto = ChallengeDto.from(challengeRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException("Challenge not found")));

        return challengeDto;
    }
}
