package com.aditya.tutorial.dto.userDtos;

import com.aditya.tutorial.entity.Enums.Roles;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {
    private Long id;

    private String name;


    private String email;

    private Roles role;
    private Long teamId;

    private LocalDateTime created_at;
}
