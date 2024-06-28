package com.hcorp.themesoflegends.controller;

import com.hcorp.themesoflegends.dto.RankHighScoreDto;
import com.hcorp.themesoflegends.service.HighScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/high-score")
public class HighScoreController {
    private final HighScoreService highScoreService;

    @Autowired
    public HighScoreController(HighScoreService highScoreService) {
        this.highScoreService = highScoreService;
    }

    @GetMapping("/list")
    public List<RankHighScoreDto> getHighScore(@RequestParam("round") int round) {
        return this.highScoreService.getRankHighScore(round);
    }
}
