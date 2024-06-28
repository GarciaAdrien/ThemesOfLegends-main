package com.hcorp.themesoflegends.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerResponseDto {
    private String musicToken;
    private String proposition;
    private String type;
    private String date;
}
