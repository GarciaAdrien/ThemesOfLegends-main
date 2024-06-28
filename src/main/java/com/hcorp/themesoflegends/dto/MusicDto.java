package com.hcorp.themesoflegends.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MusicDto {
    private String token;
    private String name;
    private String date;
    private String type;
    private List<String> aliases;
}
