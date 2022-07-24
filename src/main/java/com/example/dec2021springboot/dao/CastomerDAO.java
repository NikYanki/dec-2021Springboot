package com.example.dec2021springboot.dao;

import com.example.dec2021springboot.models.Castomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastomerDAO extends JpaRepository<Castomer, Integer> {
    Castomer findByLogin(String login);
}
