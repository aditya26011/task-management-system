package com.aditya.tutorial.dto;

import com.aditya.tutorial.entity.Enums.Roles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpDto {

    private String name;
    private String email;
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Roles role;
}
