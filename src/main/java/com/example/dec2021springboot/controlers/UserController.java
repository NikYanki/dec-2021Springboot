package com.example.dec2021springboot.controlers;

import com.example.dec2021springboot.models.User;
import com.example.dec2021springboot.models.dto.UserPasportReqestDTO;
import com.example.dec2021springboot.models.dto.UserPassportResponceDTO;
import com.example.dec2021springboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

//    private UserDAO userDAO;
    private UserService userService;
//    private PassportDAO passportDAO;
//    public UserController(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
    }
    @PostMapping("")
    public void saveUser(@RequestBody @Valid User user){
        userService.saveuser(user);
    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteById(id);
    }
//    @PatchMapping("")
//    public ResponseEntity<User> patchUser(@RequestBody User user){
//        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.ACCEPTED);
//    }
    @PatchMapping("")
    public ResponseEntity<UserPassportResponceDTO> updateUser(@RequestBody UserPasportReqestDTO dto){

        return new ResponseEntity(userService.updatteUserWithPasport(dto),HttpStatus.ACCEPTED);
    }

}
