package com.hcorp.themesoflegends.controller;

import com.hcorp.themesoflegends.dto.GameDto;
import com.hcorp.themesoflegends.dto.MusicDto;
import com.hcorp.themesoflegends.dto.PlayerResponseDto;
import com.hcorp.themesoflegends.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public GameDto createGame(@RequestParam("userUid") String userUid, @RequestParam("roundToPlay") int roundToPlay) {
        return this.gameService.createNewGame(userUid, roundToPlay);
    }

    @DeleteMapping("/delete")
    public void deleteGame(@RequestParam("gameId") String gameId) {
        this.gameService.deleteGame(gameId);
    }

    @GetMapping("/play-round")
    public MusicDto getRoundMusic(@RequestParam("gameId") String gameId) {
        return this.gameService.getRoundMusic(gameId);
    }

    @PostMapping("/player-response")
    public GameDto treatPlayerResponse(@RequestParam("gameId") String gameId, @RequestBody PlayerResponseDto playerResponseDto) {
        return this.gameService.treatPlayerResponse(gameId, playerResponseDto);
    }
}
