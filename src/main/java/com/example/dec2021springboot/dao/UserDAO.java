package com.example.dec2021springboot.dao;

import com.example.dec2021springboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Integer> {
}
