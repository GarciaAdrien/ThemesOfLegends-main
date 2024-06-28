package com.hcorp.themesoflegends.service;

import com.hcorp.themesoflegends.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    private final MusicService musicService;
    private final UserService userService;
    private final GameManager gameManager;

    @Autowired
    public GameService(MusicService musicService, UserService userService, GameManager gameManager) {
        this.musicService = musicService;
        this.userService = userService;
        this.gameManager = gameManager;
    }

    public GameDto createNewGame(String userUid, int roundToPlay) {
        UserDto userDto = this.userService.findUserByUid(userUid);
        if (userDto != null) {
            GameDto game = GameDto.builder()
                    .gameId(UUID.randomUUID().toString())
                    .player(PlayerDto.builder()
                            .name(userDto.getName())
                            .uid(userDto.getUid())
                            .score(0)
                            .combo(0)
                            .mastery("")
                            .build())
                    .roundToPlay(roundToPlay)
                    .round(0)
                    .musicPlayed(new ArrayList<>())
                    .build();
            this.gameManager.addGame(game);
            return game;
        }
        return null;
    }

    public void deleteGame(String gameId) {
        this.gameManager.removeGame(gameId);
    }

    public MusicDto getRoundMusic(String gameId) {
        GameDto game = this.gameManager.getGame(gameId);
        if (game != null) {
            MusicDto music;
            do {
                music = musicService.getRandomMusic();
            } while (game.getMusicPlayed().contains(music));

            game.getMusicPlayed().add(music);
            return music;
        }
        return null;
    }

    public GameDto treatPlayerResponse(String gameId, PlayerResponseDto playerResponse) {
        GameDto game = this.gameManager.getGame(gameId);
        MusicDto musicDto = this.musicService.findMusic(playerResponse.getMusicToken());
        if (game != null) {
            //points management
            int pointCounter = 0;
            int comboCounter = 0;
            if (playerResponse.getDate().equals(musicDto.getDate())) {
                pointCounter += 50;
                comboCounter += 1;
            }
            if (playerResponse.getType().equals(musicDto.getType())) {
                pointCounter += 100;
                comboCounter += 1;
            }
            if (playerResponse.getProposition().toLowerCase().equals(musicDto.getName())) {
                pointCounter += 150;
                comboCounter += 1;
            } else if (musicDto.getAliases().contains(playerResponse.getProposition().toLowerCase())) {
                pointCounter += 100;
            }

            //combo management
            if (comboCounter == 3 && game.getPlayer().getCombo() < 5) {
                game.getPlayer().setCombo(Math.min(game.getPlayer().getCombo() + 1, 5));
            }
            if (comboCounter == 1) {
                game.getPlayer().setCombo(Math.max(game.getPlayer().getCombo() - 1, 0));
            }
            if (comboCounter == 0) {
                game.getPlayer().setCombo(0);
            }

            //player update
            game.getPlayer().setScore(game.getPlayer().getScore() + (int) (pointCounter + (pointCounter * game.getPlayer().getCombo() * 0.2)));

            //round management
            game.setRound(game.getRound() + 1);
            if (game.getRound() == game.getRoundToPlay()) {
                this.endGame(game);
            }
            return game;
        }
        return null;
    }

    private void endGame(GameDto game) {
        game.setOver(true);

        //mastery management
        int maxPoint = this.getMaxScore(game.getRoundToPlay());
        if (game.getPlayer().getScore() > maxPoint * 0.85) {
            game.getPlayer().setMastery("M7");
        } else if (game.getPlayer().getScore() > maxPoint * 0.70) {
            game.getPlayer().setMastery("M6");
        } else if (game.getPlayer().getScore() > maxPoint * 0.55) {
            game.getPlayer().setMastery("M5");
        } else if (game.getPlayer().getScore() > maxPoint * 0.40) {
            game.getPlayer().setMastery("M4");
        } else if (game.getPlayer().getScore() > maxPoint * 0.25) {
            game.getPlayer().setMastery("M3");
        } else if (game.getPlayer().getScore() > maxPoint * 0.10) {
            game.getPlayer().setMastery("M2");
        } else {
            game.getPlayer().setMastery("M1");
        }
        //user update management
        this.userService.updateScore(game.getPlayer().getUid(), game.getPlayer().getScore(), game.getPlayer().getMastery(), game.getRoundToPlay());

        //game repository management
        this.gameManager.removeGame(game.getGameId());
    }

    private int getMaxScore(int roundToPlay) {
        int points = 0;
        int combo = 0;

        for (int i = 0; i < roundToPlay; i++) {
            points += (int) (300 * (1 + 0.2 * Math.min(combo, 5)));
            combo++;
        }

        return points;
    }
}
