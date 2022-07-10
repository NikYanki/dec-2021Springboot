package com.example.dec2021springboot.controlers;

import com.example.dec2021springboot.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {
    ArrayList<User> users = new ArrayList<>();

    MainController() {
        users.add(new User(1, "valera"));
        users.add(new User(2, "vasya"));
        users.add(new User(3, "volodya"));
    }

    @GetMapping("/")
    public void foobar() {
        System.out.println("hello");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<String>("this is body responce", HttpStatus.OK);
        return stringResponseEntity;
    }

    @GetMapping("/users")
    public List<User> getUsers() {

        return users;
    }

    @PostMapping("/users/save")
    public ResponseEntity<List<User>> saveUser(@RequestBody User user) {
        users.add(user);
        return new ResponseEntity<>(users,HttpStatus.CREATED);
    }

    @PostMapping("/users")
    public ResponseEntity<List<User>> saveUser(@RequestParam int id, @RequestParam String name) {
        users.add(new User(id,name));
        return new ResponseEntity<>(users,HttpStatus.CREATED);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
       User u =  users.stream().filter(user -> user.getId()==id).collect(Collectors.toList()).get(0);
       return new ResponseEntity<>(u,HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        users.removeIf(next -> next.getId() == id);
    }

}
