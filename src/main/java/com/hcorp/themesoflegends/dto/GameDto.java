package com.hcorp.themesoflegends.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GameDto {
    private String gameId;
    private PlayerDto player;
    private int roundToPlay;
    private int round;
    private boolean isOver;
    private List<MusicDto> musicPlayed;
}
