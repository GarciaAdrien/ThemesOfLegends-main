package com.hcorp.themesoflegends.service;

import com.hcorp.themesoflegends.dto.AvatarDto;
import com.hcorp.themesoflegends.dto.UserDto;
import com.hcorp.themesoflegends.entity.Avatar;
import com.hcorp.themesoflegends.entity.User;
import com.hcorp.themesoflegends.entity.UserAvatar;
import com.hcorp.themesoflegends.repositopry.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final HighScoreService highScoreService;
    private final AvatarService avatarService;
    private final UserAvatarService userAvatarService;

    @Autowired
    public UserService(UserRepository userRepository,
                       HighScoreService highScoreService,
                       AvatarService avatarService,
                       UserAvatarService userAvatarService) {
        this.userRepository = userRepository;
        this.highScoreService = highScoreService;
        this.avatarService = avatarService;
        this.userAvatarService = userAvatarService;
    }

    public UserDto createUser(String userName, String email, String userPassword) {
        if (this.userRepository.findByName(userName).isEmpty()) {
            User newUser = User.builder()
                    .name(userName)
                    .uid(UUID.randomUUID().toString())
                    .email(email)
                    .password(userPassword)
                    .gamePlayed(0L)
                    .totalScore(0L)
                    .build();

            newUser.setHighScore(this.highScoreService.createHighScore(newUser));
            newUser = this.userRepository.save(newUser);

            for (Avatar avatar : avatarService.getAllAvatars()) {
                UserAvatar userAvatar = UserAvatar.builder()
                        .user(newUser)
                        .avatar(avatar)
                        .isSelectable(avatar.getToken().equals("Globouille"))
                        .isSelected(avatar.getToken().equals("Globouille"))
                        .build();
                userAvatarService.createUserAvatar(userAvatar);
            }
        }
        return null;
    }

    public UserDto connectUser(String userName, String userPassword) {
        Optional<User> user = this.userRepository.findByName(userName);
        if (user.isPresent() && userPassword.equals(user.get().getPassword())) {
            return this.convertToDto(user.get());
        }
        return null;
    }

    public UserDto getUser(String uid) {
        Optional<User> user = this.userRepository.findByUid(uid);
        return user.map(this::convertToDto).orElse(null);
    }

    public UserDto findUserByUid(String userUid) {
        Optional<User> user = this.userRepository.findByUid(userUid);
        return user.map(this::convertToDto).orElse(null);
    }

    public List<AvatarDto> updateAvatar(String userUid, Long avatarId) {
        Optional<User> optionalUser = this.userRepository.findByUid(userUid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserAvatar newUserAvatar = this.userAvatarService.findAvatarByUserIdAndAvatarId(user.getId(), avatarId);
            if (newUserAvatar.isSelectable()) {
                UserAvatar previousUserAvatar = this.userAvatarService.findSelectedAvatarByUserId(user.getId());
                if (previousUserAvatar != null) {
                    previousUserAvatar.setSelected(false);
                    this.userAvatarService.updateUserAvatar(previousUserAvatar);
                }
                newUserAvatar.setSelected(true);
                this.userAvatarService.updateUserAvatar(newUserAvatar);
            }
            return this.getUserAvatars(userUid);
        }
        return new ArrayList<>();
    }

    public List<AvatarDto> buyAvatar(String userUid, Long avatarId) {
        Optional<User> optionalUser = this.userRepository.findByUid(userUid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserAvatar newUserAvatar = this.userAvatarService.findAvatarByUserIdAndAvatarId(user.getId(), avatarId);
            if (!newUserAvatar.isSelectable() && user.getTotalScore() >= newUserAvatar.getAvatar().getPrice()) {
                newUserAvatar.setSelectable(true);
                user.setTotalScore(user.getTotalScore() - newUserAvatar.getAvatar().getPrice());
                this.userRepository.save(user);
                this.userAvatarService.updateUserAvatar(newUserAvatar);
            }
        }
        return this.getUserAvatars(userUid);
    }

    public UserDto updatePassword(String userUid, String newPassword) {
        Optional<User> optionalUser = this.userRepository.findByUid(userUid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(newPassword);
            return this.convertToDto(this.userRepository.save(user));
        }
        return null;
    }

    public void updateScore(String userUid, int score, String mastery, int roundPlayed) {
        Optional<User> optionalUser = this.userRepository.findByUid(userUid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setGamePlayed(user.getGamePlayed() + 1);
            user.setTotalScore(user.getTotalScore() + score);
            user.getHighScore().forEach(highScoreDto -> {
                if (highScoreDto.getRoundNumber() == roundPlayed && highScoreDto.getHighScoreValue() < score) {
                    highScoreDto.setHighScoreValue((long) score);
                    highScoreDto.setMastery(mastery);
                }
            });
            this.userRepository.save(user);
        }
    }

    public List<AvatarDto> getUserAvatars(String userUid) {
        Optional<User> optionalUser = this.userRepository.findByUid(userUid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return this.userAvatarService.findAvatarsByUserId(user.getId());
        }
        return new ArrayList<>();
    }

    private UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .uid(user.getUid())
                .avatarToken(this.userAvatarService.findSelectedAvatarByUserId(user.getId()).getAvatar().getToken())
                .email(user.getEmail())
                .gamePlayed(user.getGamePlayed())
                .highScore(user.getHighScore().stream()
                        .map(this.highScoreService::convertToDto)
                        .toList())
                .totalScore(user.getTotalScore())
                .build();
    }
}
