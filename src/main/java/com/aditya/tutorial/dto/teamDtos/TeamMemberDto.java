package com.aditya.tutorial.dto.teamDtos;

import com.aditya.tutorial.entity.Enums.Roles;
import lombok.Data;

@Data
public class TeamMemberDto {
    private Long id;
    private String name;
    private String email;
    private Roles role;
}
