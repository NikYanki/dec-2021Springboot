package com.example.dec2021springboot.controlers;

import com.example.dec2021springboot.dao.UserDAO;
import com.example.dec2021springboot.models.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserDAO userDAO;
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
//    @GetMapping("")
//    public ResponseEntity<List<User>> getAllUsers(){
//        return new ResponseEntity<>(userDAO.findAll(), HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable int id){
//        return new ResponseEntity<>(userDAO.findById(id).get(),HttpStatus.OK);
//    }
//    @PostMapping("")
//    public void saveUser(@RequestBody User user){
//        userDAO.save(user);
//    }
//    @DeleteMapping("/{id}")
//    public void deleteUserById(@PathVariable int id){
//        userDAO.deleteById(id);
//    }
//    @PatchMapping("")
//    public ResponseEntity<User> patchUser(@RequestBody User user){
//        return new ResponseEntity<>(userDAO.save(user),HttpStatus.ACCEPTED);
//    }


}
