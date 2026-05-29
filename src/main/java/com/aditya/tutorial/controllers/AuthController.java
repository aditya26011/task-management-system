package com.aditya.tutorial.controllers;

import com.aditya.tutorial.dto.LoginDto;
import com.aditya.tutorial.dto.LoginResponseDto;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

      String msg= authService.login(loginDto);
      return new ResponseEntity<>(msg,HttpStatus.OK);
    }

}
