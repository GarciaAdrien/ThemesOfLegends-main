package com.hcorp.themesoflegends.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class HighScoreDto {
    private Long roundNumber;
    private Long highScoreValue;
    private String mastery;
}
