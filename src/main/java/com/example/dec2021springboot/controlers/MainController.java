package com.example.dec2021springboot.controlers;

import com.example.dec2021springboot.dao.UserDAO;
import com.example.dec2021springboot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.awt.*;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class MainController {
    UserDAO userDAO;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public void saveUser(@RequestBody User user) {
        userDAO.save(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userDAO.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> byId = userDAO.findById(id);
        User user = byId.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/byname")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam String name) {
        //List<User> users = userDAO.findMeAUserWithName(name);
        List<User> users = userDAO.findByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @PatchMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User save = userDAO.save(user);
        return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id){
        userDAO.deleteById(id);
    }

}

