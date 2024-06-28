package com.hcorp.themesoflegends.service;

import com.hcorp.themesoflegends.dto.AvatarDto;
import com.hcorp.themesoflegends.entity.UserAvatar;
import com.hcorp.themesoflegends.repositopry.UserAvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAvatarService {
    private final UserAvatarRepository userAvatarRepository;

    @Autowired
    public UserAvatarService(UserAvatarRepository userAvatarRepository) {
        this.userAvatarRepository = userAvatarRepository;
    }

    public void createUserAvatar(UserAvatar userAvatar) {
        this.userAvatarRepository.save(userAvatar);
    }

    public List<AvatarDto> findAvatarsByUserId(Long id) {
        return this.userAvatarRepository.findAllByUserId(id)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public UserAvatar findSelectedAvatarByUserId(Long id) {
        return this.userAvatarRepository.findByUserIdAndSelectableIsTrue(id);
    }

    public UserAvatar findAvatarByUserIdAndAvatarId(Long avatarId, Long userId) {
        return this.userAvatarRepository.findByUserIdAndAvatarId(avatarId, userId);
    }

    public void updateUserAvatar(UserAvatar userAvatar) {
        this.userAvatarRepository.save(userAvatar);
    }

    private AvatarDto convertToDto(UserAvatar userAvatar) {
        return AvatarDto.builder()
                .id(userAvatar.getAvatar().getId())
                .token(userAvatar.getAvatar().getToken())
                .price(userAvatar.getAvatar().getPrice())
                .isSelectable(userAvatar.isSelectable())
                .price(userAvatar.getAvatar().getPrice())
                .isSelected(userAvatar.isSelected())
                .build();
    }
}
