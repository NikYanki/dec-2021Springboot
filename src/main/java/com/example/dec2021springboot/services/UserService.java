package com.example.dec2021springboot.services;

import com.example.dec2021springboot.dao.PassportDAO;
import com.example.dec2021springboot.dao.UserDAO;
import com.example.dec2021springboot.models.Passport;
import com.example.dec2021springboot.models.User;
import com.example.dec2021springboot.models.dto.UserPasportReqestDTO;
import com.example.dec2021springboot.models.dto.UserPassportResponceDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;
    private PassportDAO passportDAO;
    private MailService mailService;
    public void saveuser(User user){
        if (user.getName()!=null)
        userDAO.save(user);
        mailService.sendEmail(user);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }
    public User findById(int id){
        return userDAO.findById(id).get();
    }
    public void deleteById(int id){
        userDAO.deleteById(id);
    }
    public User updateUser(User user){
        userDAO.save(user);
        return user;
    }
    public UserPassportResponceDTO updatteUserWithPasport(UserPasportReqestDTO dto){
        int user_id = dto.getUser_id();
        int passport_id = dto.getPassport_id();
        User user = userDAO.findById(user_id).get();
        Passport passport = passportDAO.findById(passport_id).get();
        user.setPassport(passport);
        userDAO.save(user);
        UserPassportResponceDTO responceDTO = new UserPassportResponceDTO(user);
        return responceDTO;
    }
    public ResponseEntity<String> activateAccount(int id){
        User user = findById(id);
        user.setActivated(true);
        userDAO.save(user);
        return new ResponseEntity<>("account is activatd", HttpStatus.OK);
    }
}
