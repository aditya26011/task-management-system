package com.aditya.tutorial.controllers;

import com.aditya.tutorial.dto.UserDto;
import com.aditya.tutorial.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createEmployee(@Valid  @RequestBody UserDto userDto){
         UserDto user=userService.createEmployee(userDto);

         return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll(){
        List<UserDto> userDtoList=userService.getAll();
        return new ResponseEntity<>(userDtoList,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getEmpById(@PathVariable(value = "id") Long id){
        UserDto user=userService.getEmpById(id);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") Long id){
      int deleted= userService.deleteById(id);
      if(deleted==1)
          return new ResponseEntity<>("deleted Successfully",HttpStatus.OK);
      else
          return new ResponseEntity<>("failed to delete",HttpStatus.BAD_REQUEST);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> UpdateEmployee( @RequestBody UserDto userDto, @PathVariable(value = "id") Long id){
     UserDto updatedEmployee =  userService.UpdateEmployee(userDto,id);
     return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
    }
}
