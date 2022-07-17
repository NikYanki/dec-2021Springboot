package com.example.dec2021springboot.controlers;

import com.example.dec2021springboot.dao.PassportDAO;
import com.example.dec2021springboot.models.Passport;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/passports")
public class PassportController {
    private PassportDAO passportDAO;

    public PassportController(PassportDAO passportDAO) {
        this.passportDAO = passportDAO;
    }

    @GetMapping("")
    public ResponseEntity<List<Passport>> getPasports() {
        return new ResponseEntity<>(passportDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passport> getPassportById(@PathVariable int id) {
        return new ResponseEntity<>(passportDAO.findById(id).get(), HttpStatus.OK);
    }

    //    @PostMapping("")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void savePassport(@RequestBody Passport passport){
//        passportDAO.save(passport);
//    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void savePassport(@RequestParam String series, @RequestParam MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String userFolder = System.getProperty("user.home");
        File dest = new File(userFolder + File.separator + "someImages" + File.separator + originalFileName);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Passport passport = new Passport(series, originalFileName);
        passportDAO.save(passport);
    }

    @DeleteMapping("/{id}")
    public void deletePassportById(@PathVariable int id) {
        passportDAO.deleteById(id);
    }

    @PatchMapping("")
    public ResponseEntity<Passport> updatePassport(@RequestBody Passport passport) {
        return new ResponseEntity<>(passportDAO.save(passport), HttpStatus.ACCEPTED);
    }
}
