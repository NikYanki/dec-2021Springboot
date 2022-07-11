package com.example.dec2021springboot.controlers;

import com.example.dec2021springboot.dao.PassportDAO;
import com.example.dec2021springboot.models.Passport;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {
    private PassportDAO passportDAO;

    public PassportController(PassportDAO passportDAO) {
        this.passportDAO = passportDAO;
    }

//    @GetMapping("")
//    public ResponseEntity<List<Passport>> getPasports(){
//        return new ResponseEntity<>(passportDAO.findAll(), HttpStatus.OK);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Passport> getPassportById(@PathVariable int id){
//        return new ResponseEntity<>(passportDAO.findById(id).get(),HttpStatus.OK);
//    }
//    @PostMapping("")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void savePassport(@RequestBody Passport passport){
//        passportDAO.save(passport);
//    }
//    @DeleteMapping("/{id}")
//    public void deletePassportById(@PathVariable int id){
//        passportDAO.deleteById(id);
//    }
//    @PatchMapping("")
//    public ResponseEntity<Passport> updatePassport(@RequestBody Passport passport){
//        return new ResponseEntity<>(passportDAO.save(passport),HttpStatus.ACCEPTED);
//    }
}
