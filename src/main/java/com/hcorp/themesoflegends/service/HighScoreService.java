package com.hcorp.themesoflegends.service;

import com.hcorp.themesoflegends.dto.HighScoreDto;
import com.hcorp.themesoflegends.dto.RankHighScoreDto;
import com.hcorp.themesoflegends.entity.HighScore;
import com.hcorp.themesoflegends.entity.User;
import com.hcorp.themesoflegends.repositopry.HighScoreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HighScoreService {
    private final HighScoreRepository highScoreRepository;

    @Autowired
    public HighScoreService(HighScoreRepository highScoreRepository) {
        this.highScoreRepository = highScoreRepository;
    }

    public List<HighScore> createHighScore(User user) {
        List<HighScore> newHighScores = List.of(
                HighScore.builder()
                        .user(user)
                        .roundNumber(5L)
                        .highScoreValue(0L)
                        .mastery("")
                        .build(),
                HighScore.builder()
                        .user(user)
                        .roundNumber(10L)
                        .highScoreValue(0L)
                        .mastery("")
                        .build(),
                HighScore.builder()
                        .user(user)
                        .roundNumber(15L)
                        .highScoreValue(0L)
                        .mastery("")
                        .build()
        );
        newHighScores.forEach(this.highScoreRepository::save);
        return newHighScores;
    }

    public List<RankHighScoreDto> getRankHighScore(int round) {
        return this.highScoreRepository.findAllByRoundNumberOrderByHighScoreValue((long) round).stream()
                .filter(highScore -> highScore.getHighScoreValue() > 0)
                .sorted((hs1, hs2) -> Long.compare(hs2.getHighScoreValue(), hs1.getHighScoreValue()))
                .limit(50)
                .map(this::convertToToRankDto)
                .toList();
    }

    public HighScoreDto convertToDto(HighScore highScore) {
        return HighScoreDto.builder()
                .roundNumber(highScore.getRoundNumber())
                .highScoreValue(highScore.getHighScoreValue())
                .mastery(highScore.getMastery())
                .build();
    }

    public RankHighScoreDto convertToToRankDto(HighScore highScore) {
        return RankHighScoreDto.builder()
                .userName(highScore.getUser().getName())
                .uid(highScore.getUser().getUid())
                .highScore(this.convertToDto(highScore))
                .build();
    }
}
