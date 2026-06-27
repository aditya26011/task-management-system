package com.aditya.tutorial.dto.taskDtos;

import com.aditya.tutorial.entity.Enums.Priority;
import com.aditya.tutorial.entity.Enums.TaskStatus;
import com.aditya.tutorial.entity.Project;
import com.aditya.tutorial.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestDto {
    private String title;

    private String description;
    private Priority priority;

    private LocalDateTime dueDate;

    private Long projectId;
    private Long userId;
}
