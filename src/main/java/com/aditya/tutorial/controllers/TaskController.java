package com.aditya.tutorial.controllers;

import com.aditya.tutorial.dto.taskDtos.TaskRequestDto;
import com.aditya.tutorial.dto.taskDtos.TaskResponseDto;
import com.aditya.tutorial.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {


    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> create(@RequestBody TaskRequestDto taskRequestDto){
        TaskResponseDto taskResponseDto=taskService.create(taskRequestDto);
        return new ResponseEntity<>(taskResponseDto, HttpStatus.CREATED);
    }
}
