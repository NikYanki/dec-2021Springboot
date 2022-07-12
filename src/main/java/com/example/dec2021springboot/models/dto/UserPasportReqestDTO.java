package com.example.dec2021springboot.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPasportReqestDTO {
    private int user_id;
    private int passport_id;
}


