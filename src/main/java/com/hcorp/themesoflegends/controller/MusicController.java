package com.hcorp.themesoflegends.controller;


import com.hcorp.themesoflegends.dto.MusicDto;
import com.hcorp.themesoflegends.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("music")
public class MusicController {
    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/list")
    public List<MusicDto> getAllMusic() {
        return musicService.getAllMusics();
    }
}
