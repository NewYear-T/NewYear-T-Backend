package com.example.newyear.Service;


import com.example.newyear.Entity.Member;
import com.example.newyear.Repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankRepository rankRepository;

    public List<String> descMember(Long challengeId) {
        List<String> members = rankRepository.findMembersByChallengeId(challengeId);

        return members;
    }
}
