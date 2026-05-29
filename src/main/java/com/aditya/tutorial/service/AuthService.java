package com.aditya.tutorial.service;


import com.aditya.tutorial.dto.*;
import com.aditya.tutorial.entity.Enums.Roles;
import com.aditya.tutorial.entity.User;
import com.aditya.tutorial.exceptions.ResourceNotFoundException;
import com.aditya.tutorial.exceptions.UserAlreadyExistsException;
import com.aditya.tutorial.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.aditya.tutorial.entity.Enums.Roles.EMPLOYEE;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public SignUpResponseDto signUp(SignUpDto signUpDto) {
        String email= signUpDto.getEmail();
        Optional<User> sameEmail= userRepo.findByEmail(email);
        if(sameEmail.isPresent()){
            throw new UserAlreadyExistsException("user with same email exists");
        }
        else{

            User user = modelMapper.map(signUpDto, User.class);
            user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
            user.setRole(EMPLOYEE);
            User savedUser = userRepo.save(user);
            return modelMapper.map(savedUser,SignUpResponseDto.class);

        }

    }

    public String login(LoginDto loginDto) {
      User user= userRepo.findByEmail(loginDto.getEmail())
                .orElseThrow(()->new ResourceNotFoundException("user with this email doesn't exist"));

        boolean matches = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        if(matches){
           return  "login successfully";
        }else{
            throw new RuntimeException("Invalid Credentials");
        }

    }
}
