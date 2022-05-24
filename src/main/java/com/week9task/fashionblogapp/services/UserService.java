package com.week9task.fashionblogapp.services;

import com.week9task.fashionblogapp.dtos.LoginDto;
import com.week9task.fashionblogapp.dtos.RegistrationDto;
import com.week9task.fashionblogapp.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public interface UserService {
    String createUser(RegistrationDto registrationDto);
    ResponseEntity<User> login(LoginDto loginDto);
}
