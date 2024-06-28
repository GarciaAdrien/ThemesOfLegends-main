package com.hcorp.themesoflegends.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AvatarDto {
    private Long id;
    private String token;
    private Long price;
    private boolean isSelectable;
    private boolean isSelected;
}
