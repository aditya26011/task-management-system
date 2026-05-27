package com.aditya.tutorial.dto;

import com.aditya.tutorial.entity.Enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Roles role;

    @CreationTimestamp
    private LocalDateTime created_at;
}
