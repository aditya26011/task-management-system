package com.aditya.tutorial.controllers;

import com.aditya.tutorial.dto.taskDtos.*;
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
        return new ResponseEntity<>(taskGetResponseDtos,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskGetResponseDto> getTaskById(@PathVariable(value = "id")Long id){
        TaskGetResponseDto responseDto=taskService.getTaskById(id);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<TaskGetResponseDto> updateTask(@PathVariable(value = "id")Long id,
                                                         @RequestBody TaskUpdateDto taskUpdateDto){

        TaskGetResponseDto updatedTask=taskService.updateTask(id,taskUpdateDto);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }
    @PatchMapping("/status/{id}")
    public ResponseEntity<TaskGetResponseDto> updateStatus(@PathVariable(value = "id") Long id, @RequestBody UpdateStatusDto updateStatusDto){
        TaskGetResponseDto taskGetResponseDto=taskService.updateStatus(id,updateStatusDto);
        return new ResponseEntity<>(taskGetResponseDto,HttpStatus.OK);
    }
    @PatchMapping("/{id}/assign")
    public ResponseEntity<TaskGetResponseDto> assignTask(@PathVariable(value = "id")Long id, @RequestBody AssignTaskDto assignTaskDto){
        TaskGetResponseDto taskGetResponseDto=taskService.assignTask(id,assignTaskDto);
        return new ResponseEntity<>(taskGetResponseDto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable(value = "id")Long id){
        boolean isDeleted=taskService.deleteTask(id);
        if(isDeleted){
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>("Failed to Delete",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/my-tasks")
    public ResponseEntity<List<UserTaskDto>> getMyTasks(){
        List<UserTaskDto> userTaskDtoList=taskService.getMyTasks();
        return new ResponseEntity<>(userTaskDtoList,HttpStatus.OK);
    }

}
