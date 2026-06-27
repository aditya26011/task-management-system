package com.aditya.tutorial.controllers;

import com.aditya.tutorial.dto.taskDtos.TaskGetResponseDto;
import com.aditya.tutorial.dto.taskDtos.TaskRequestDto;
import com.aditya.tutorial.dto.taskDtos.TaskResponseDto;
import com.aditya.tutorial.dto.taskDtos.TaskUpdateDto;
import com.aditya.tutorial.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
      public ResponseEntity<List<TaskGetResponseDto>> getAll(){
        List<TaskGetResponseDto> taskGetResponseDtos=taskService.getAllTask();
        return new ResponseEntity<>(taskGetResponseDtos,HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskGetResponseDto> getTaskById(@PathVariable(value = "id")Long id){
        TaskGetResponseDto responseDto=taskService.getTaskById(id);
        return new ResponseEntity<>(responseDto,HttpStatus.FOUND);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<TaskGetResponseDto> updateTask(@PathVariable(value = "id")Long id,
                                                         @RequestBody TaskUpdateDto taskUpdateDto){

        TaskGetResponseDto updatedTask=taskService.updateTask(id,taskUpdateDto);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }
}
