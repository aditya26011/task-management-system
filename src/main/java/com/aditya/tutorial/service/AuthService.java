package com.aditya.tutorial.service;


import com.aditya.tutorial.dto.SignUpDto;
import com.aditya.tutorial.dto.SignUpResponseDto;
import com.aditya.tutorial.dto.UserDto;
import com.aditya.tutorial.entity.Enums.Roles;
import com.aditya.tutorial.entity.User;
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
}
