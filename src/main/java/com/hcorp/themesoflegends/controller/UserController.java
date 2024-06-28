package com.hcorp.themesoflegends.controller;

import com.hcorp.themesoflegends.dto.AvatarDto;
import com.hcorp.themesoflegends.dto.UserDto;
import com.hcorp.themesoflegends.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserDto createUser(@RequestParam("name") String userName, @RequestParam("email") String userEmail, @RequestParam("password") String userPassword) {
        return this.userService.createUser(userName, userEmail, userPassword);
    }

    @PutMapping("/change-avatar")
    public List<AvatarDto> updateAvatar(@RequestParam("uid") String userUid, @RequestParam("avatar") Long newAvatarId) {
        return this.userService.updateAvatar(userUid, newAvatarId);
    }

    @PutMapping("/buy-avatar")
    public List<AvatarDto> buyAvatar(@RequestParam("uid") String userUid, @RequestParam("avatar") Long newAvatarId) {
        return this.userService.buyAvatar(userUid, newAvatarId);
    }

    @PutMapping("/change-password")
    public UserDto updatePassword(@RequestParam("uid") String userUid, @RequestParam("password") String newPassword) {
        return this.userService.updatePassword(userUid, newPassword);
    }

    @GetMapping("/connect")
    public UserDto connectUser(@RequestParam("name") String userName, @RequestParam("password") String userPassword) {
        return this.userService.connectUser(userName, userPassword);
    }

    @GetMapping("/get")
    public UserDto getUser(@RequestParam("uid") String uid) {
        return this.userService.getUser(uid);
    }

    @GetMapping("/avatars")
    public List<AvatarDto> getUserAvatars(@RequestParam("uid") String userUid) {
        return this.userService.getUserAvatars(userUid);
    }
}
