package com.aditya.tutorial.controllers;

import com.aditya.tutorial.dto.projectDtos.ProjectRequestDto;
import com.aditya.tutorial.dto.projectDtos.ProjectResponseDto;
import com.aditya.tutorial.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto projectDto){
        ProjectResponseDto projectDto1=projectService.createProject(projectDto);

        return new ResponseEntity<>(projectDto1, HttpStatus.CREATED);
    }
}
