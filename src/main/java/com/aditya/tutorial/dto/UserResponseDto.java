package com.aditya.tutorial.dto;

import com.aditya.tutorial.entity.Enums.Roles;
import com.aditya.tutorial.entity.Team;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

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
