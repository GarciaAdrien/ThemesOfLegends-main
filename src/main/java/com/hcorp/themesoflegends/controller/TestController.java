package com.hcorp.themesoflegends.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String testApi() {
        return "L'API Themes of Legends est opérationnel";
    }
}
