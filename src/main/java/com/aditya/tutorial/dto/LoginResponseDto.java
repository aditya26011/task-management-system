package com.aditya.tutorial.dto;


import lombok.Data;

@Data
public class LoginResponseDto {

    private Long id;
    private String email;
    private String token;

}
