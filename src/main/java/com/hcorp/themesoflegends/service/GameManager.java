package com.hcorp.themesoflegends.service;

import com.hcorp.themesoflegends.dto.GameDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GameManager {
    private final Map<String, GameDto> games;

    public GameManager() {
       this.games = new HashMap<>();
    }

    public void addGame(GameDto game) {
        this.games.put(game.getGameId(), game);
    }

    public GameDto getGame(String gameId) {
        return this.games.get(gameId);
    }

    public void removeGame(String gameId) {
        this.games.remove(gameId);
    }
}
