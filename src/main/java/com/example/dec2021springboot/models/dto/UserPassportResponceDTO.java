package com.example.dec2021springboot.models.dto;

import com.example.dec2021springboot.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPassportResponceDTO {
    private String userName;
    private String passportSeries;
    public UserPassportResponceDTO(User user){
        this.userName = user.getName();
        this.passportSeries = user.getPassport().getSeries();
    }
}
