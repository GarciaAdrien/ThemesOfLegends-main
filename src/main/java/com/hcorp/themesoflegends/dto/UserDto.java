package com.hcorp.themesoflegends.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String uid;
    private String email;
    private String avatarToken;
    private Long gamePlayed;
    private List<HighScoreDto> highScore;
    private Long totalScore;
}
