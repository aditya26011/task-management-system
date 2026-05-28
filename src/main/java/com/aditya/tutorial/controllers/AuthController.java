package com.aditya.tutorial.controllers;

import com.aditya.tutorial.dto.SignUpDto;
import com.aditya.tutorial.dto.SignUpResponseDto;
import com.aditya.tutorial.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpDto signUpDto){
        SignUpResponseDto responseDto=authService.signUp(signUpDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
