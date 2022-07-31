package com.example.dec2021springboot.controllers;

import com.example.dec2021springboot.dao.CastomerDAO;
import com.example.dec2021springboot.models.Castomer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    CastomerDAO castomerDAO;
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String open() {
        return "page open";
    }

    @PostMapping("/")
    public String postMapping() {
        return "postMapping done";
    }

    @GetMapping("/users")
    public ResponseEntity<List<Castomer>> getMapping() {
       List<Castomer> users =castomerDAO.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users")
    public void saveUser(@RequestBody Castomer castomer) {
        String encode = passwordEncoder.encode(castomer.getPassword());
        castomer.setPassword(encode);
        castomerDAO.save(castomer);
    }
}
