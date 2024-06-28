package com.hcorp.themesoflegends.service;

import com.hcorp.themesoflegends.entity.Avatar;
import com.hcorp.themesoflegends.repositopry.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public List<Avatar> getAllAvatars() {
        return this.avatarRepository.findAll();
    }
}
