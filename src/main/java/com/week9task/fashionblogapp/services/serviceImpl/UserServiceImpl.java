package com.week9task.fashionblogapp.services.serviceImpl;

import com.week9task.fashionblogapp.dtos.LoginDto;
import com.week9task.fashionblogapp.dtos.RegistrationDto;
import com.week9task.fashionblogapp.entities.User;
import com.week9task.fashionblogapp.exceptions.CustomRequestException;
import com.week9task.fashionblogapp.repositories.UserRepository;
import com.week9task.fashionblogapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public String createUser(RegistrationDto registrationDto) {
        User user = new User();
        User byUserName = userRepository.findByUserName(registrationDto.getUserName());

        if( byUserName == null){
            user.setFirstName(registrationDto.getFirstName());
            user.setLastName(registrationDto.getLastName());
            user.setEmail(registrationDto.getEmail());
            user.setUserName(registrationDto.getUserName());
            user.setPassword(registrationDto.getPassword());

            userRepository.save(user);
            return "Registration Successful";
        }

            throw new CustomRequestException("This user already exists. Go to Login");
    }

    @Override
    public ResponseEntity<User> login(LoginDto loginDto) {
        User byUserNameAndPassword = userRepository.findByUserNameAndPassword(loginDto.getUserName(),
                                   loginDto.getPassword());

        if(byUserNameAndPassword == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

      return new ResponseEntity<>(byUserNameAndPassword, HttpStatus.OK);
    }



}
