package com.example.dec2021springboot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {

    @GetMapping("/")
    public String open() {
        return "page open";
    }

    @PostMapping("/")
    public String postMapping() {
        return "postMapping done";
    }
}
