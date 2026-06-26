package com.aditya.tutorial.dto.projectDtos;

import com.aditya.tutorial.entity.Enums.Status;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProjectRequestDto {

    private String name;
    private String description;

    private Timestamp startDate;
    private Timestamp endDate;
    private Long teamId;


}
